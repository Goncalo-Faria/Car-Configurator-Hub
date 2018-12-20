public class ClasseComponente {
	private TipoComponente tipoComponente;
	private int id;
	private boolean eObrigatorio;
	private String nome;

	public ClasseComponente(TipoComponente tipoComponente, int id,
							boolean eObrigatorio, String nome) {
		this.tipoComponente = tipoComponente;
		this.id = id;
		this.eObrigatorio = eObrigatorio;
		this.nome = nome;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isEObrigatorio() {
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

}