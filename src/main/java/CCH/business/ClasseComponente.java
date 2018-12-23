package CCH.business;

public class ClasseComponente {
	private TipoComponente tipoComponente;
	private int id;
	private boolean eObrigatorio;
	private String nome;

	private static int nextId = 1;

	public ClasseComponente(TipoComponente tipoComponente,
							boolean eObrigatorio, String nome) {
		this.tipoComponente = tipoComponente;
		this.id = ClasseComponente.getNextId();
		this.eObrigatorio = eObrigatorio;
		this.nome = nome;
	}

	public ClasseComponente(int id, boolean eObrigatorio, String nome, TipoComponente tipoComponente) {
		this.tipoComponente = tipoComponente;
		this.id = id;
		this.eObrigatorio = eObrigatorio;
		this.nome = nome;
	}

	public static int getNextId() {
		return nextId++;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean getEObrigatorio() {
		return this.eObrigatorio;
	}

	public void setEObrigatorio(boolean eObrigatorio) {
		this.eObrigatorio = eObrigatorio;
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
}