package CCH.business;

import CCH.dataaccess.RemoteClass;

import java.util.LinkedList;
import java.util.List;

public class ClasseComponente implements RemoteClass<Integer> {

	private TipoComponente tipoComponente;
	private int id;
	private boolean eObrigatorio;
	private String nome;

	public int getId() {
		return this.id;
	}

	public Integer key(){return this.id; }

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

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public TipoComponente getTipoComponente() {
		return tipoComponente;
	}

	public void setTipoComponente(TipoComponente tipoComponente) {
		this.tipoComponente = tipoComponente;
	}

	public boolean getEObrigatorio() {
		return eObrigatorio;
	}

	public void setEObrigatorio(boolean eObrigatorio) {
		this.eObrigatorio = eObrigatorio;
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