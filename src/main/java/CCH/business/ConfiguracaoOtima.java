package CCH.business;

import ilog.concert.*;
import ilog.cplex.IloCplex;

import java.util.HashMap;
import java.util.List;
import java.util.Set;


public class ConfiguracaoOtima {

    private void restricaoComponentes(IloCplex cplex, List<Componente> componentesObrigatorios, List<Componente> componentes, HashMap<Integer, IloIntVar> comps) throws IloException {
        for (Componente c : componentes) {
            IloIntVar value = comps.get(c.getId());
            Set<Integer> incomp = c.getIncompativeis().keySet();

            for (Integer k : incomp) {
                if (comps.containsKey(k) && c.getId() < k) {
                    IloIntVar kvalue = comps.get(k);
                    cplex.addLe(cplex.sum(value,kvalue),1);
                } else if (c.getId() > k) {//otimização para não repetir restrições uma vez que a incompatibilidade funciona nas duas direções
                    cplex.addEq(0, value);//se o componente não estiver em comps o modelo não o seleciona, Para prevenir Erros.
                }
            }

            Set<Integer> necessarios = c.getRequeridos().keySet();

            for (Integer k:necessarios) {
                if (comps.containsKey(k)) {
                    IloIntVar kvalue = comps.get(k);
                    cplex.addLe(value,kvalue);//se a precisa de b entao a<=b
                } else {
                    cplex.addEq(0, value);//se o componente não estiver em comps o modelo não o seleciona, Para prevenir Erros.
                }
            }
        }

        //componentes obrigatórios
        for (Componente c:componentesObrigatorios) {
            if(!comps.containsKey(c.getId())) {
                throw new IloException();
            }

            cplex.addEq(comps.get(c.getId()),1);
            //garante que os componentes obrigatórios são selecionados
            //está aqui para garantir que não ocorrem incompatibilidades
        }
    }

    private void restricaoPacotes(IloCplex cplex, List<Pacote> pacotes, List<Componente> componentes, HashMap<Integer, IloIntVar> comps, HashMap<Integer, IloIntVar> pacs) throws IloException{
        HashMap<Integer,IloNumExpr> overlap= new HashMap<>();

        for (Pacote p : pacotes) {
            IloIntVar pvalue = pacs.get(p.getId());
            Set<Integer> cs = p.getComponentes().keySet();

            for (Integer k:cs) {
                if (comps.containsKey(k)) {
                    IloIntVar kvalue = comps.get(k);
                    cplex.addLe(pvalue,kvalue);//se a precisa de b entao a<=b
                    // construção das restrições de overlap de pacotes

                    if(!overlap.containsKey(k)) {
                        overlap.put(k,cplex.constant(0));
                    }

                    IloNumExpr exp = overlap.get(k);
                    exp = cplex.sum(exp,pvalue);
                } else {
                    cplex.addEq(0,pvalue);//se o componente não estiver em comps o modelo não o seleciona, Para prevenir Erros.
                }
            }
        }
        for (IloNumExpr exp:overlap.values())
            cplex.addLe(exp,1);//apenas um pacote pode conter um componente
    }


    public Configuracao configuracaoOtima(
            List<Componente> componentesObrigatorios,
            List<Componente> componentes,
            List<Pacote> pacotes,
            double money
    ) throws IloException {

        IloCplex cplex = new IloCplex();
        HashMap<Integer,IloIntVar> comps = new HashMap<>();
        HashMap<Integer,IloIntVar> pacs = new HashMap<>();

        IloNumExpr objfunc = cplex.constant(0);

        //Variaveis de decisão
        for (Componente c:componentes) {
            int ckey = c.getId();
            IloIntVar cvalue = cplex.boolVar("c"+ckey);
            comps.put(ckey,cvalue);
            objfunc = cplex.sum(objfunc,cplex.prod(cvalue,c.getPreco())); //função objetivo
        }
        for (Pacote p:pacotes) {
            int pkey = p.getId();
            IloIntVar pvalue = cplex.boolVar("p"+pkey);
            comps.put(pkey,pvalue);
            objfunc = cplex.sum(objfunc,cplex.prod(pvalue,-1*p.getDesconto()));//função objetivo
        }

        //Função Objetivo
        cplex.addMaximize(objfunc);

        //restrições de pacotes e componentes
        restricaoComponentes(cplex,componentesObrigatorios,componentes,comps);
        restricaoPacotes(cplex,pacotes,componentes,comps,pacs);

        //restrição de preço
        cplex.addLe(money,objfunc);

        //resolve o problema
        if(cplex.solve()) {
            //retornar configuração
            Configuracao otima = new Configuracao();
            for (Pacote p: pacotes) {
                //if() otima.adicionarPacote();
            }
            for (Componente c: componentes) {
                //if() otima.adiconarComponente();
            }
            return otima;
        }
        else throw new IloException();
    }
}
