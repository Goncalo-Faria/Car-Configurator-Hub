package CCH.business;

import CCH.dataaccess.RemoteClass;

import java.util.LinkedList;
import java.util.List;

public class ClasseComponente implements RemoteClass<Integer> {

	private final TipoComponente tipoComponente;
	private final int id;
	private final boolean eObrigatorio;
	private final String nome;

	public int getId() {
		return this.id;
	}

	public Integer key(){return this.id; }

    public Integer key(String k) {
        return Integer.valueOf(k);
    }

	@Override
	public ClasseComponente fromRow(List<String> row) {
		return new ClasseComponente(row);
	}

	@Override
	public List<String> toRow() {
		List<String> l = new LinkedList<String>();
		l.add(String.valueOf(this.id));
		l.add(String.valueOf(this.eObrigatorio));
		l.add(this.nome);
		l.add(String.valueOf(this.tipoComponente.getValue()));
		return l;
	}

	public String getNome() {
		return this.nome;
	}

	public TipoComponente getTipoComponente() {
		return tipoComponente;
	}

	public boolean getEObrigatorio() {
		return eObrigatorio;
	}


	public ClasseComponente(int id, boolean eObrigatorio, String nome, TipoComponente tipoComponente) {
		this.tipoComponente = tipoComponente;
		this.id = id;
		this.eObrigatorio = eObrigatorio;
		this.nome = nome;
	}

	public ClasseComponente(List<String> rs){
		this.id = Integer.valueOf(rs.get(0));
		this.eObrigatorio = Boolean.valueOf(rs.get(1));
		this.nome= rs.get(2);
		this.tipoComponente = TipoComponente.withValue(Integer.valueOf(rs.get(3)));
	}


}