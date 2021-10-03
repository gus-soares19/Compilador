package models;

public class Token implements Constants {
	private int id;
	private String lexeme;
	private int position;
	private int linha;

	public Token(int id, String lexeme, int position) {
		this.id = id;
		this.lexeme = lexeme;
		this.position = position;
	}

	public boolean isPalavraReservada() {
		for (int i = 0; i < SPECIAL_CASES_VALUES.length; i++) {
			if (SPECIAL_CASES_VALUES[i] == this.getId()) {
				return true;
			}
		}
		return false;
	}

	public boolean isIdentificador() {
		if (this.getId() == 2) {
			return true;
		}
		return false;
	}

	public String getTipo() {
		if (isIdentificador()) {
			return this.getLexeme() + " | identificador | " + this.getLinha() + "\n";
		}

		if (isPalavraReservada()) {
			return this.getLexeme() + " | palavra reservada | " + this.getLinha() + "\n";
		}

		if (isSimboloEspecial()) {
			return this.getLexeme() + " | símbolo especial | " + this.getLinha() + "\n";
		}

		switch (this.getId()) {

		case 3:
			return this.getLexeme() + " | constante int | " + this.getLinha() + "\n";

		case 4:
			return this.getLexeme() + " | constante float | " + this.getLinha() + "\n";

		case 5:
			return this.getLexeme() + " | constante char | " + this.getLinha() + "\n";

		case 6:
			return this.getLexeme() + " | constante str | " + this.getLinha() + "\n";

		}
		return null;
	}

	public boolean isSimboloEspecial() {
		if (this.getId() >= 17) {
			return true;
		}
		return false;
	}

	public final int getId() {
		return id;
	}

	public final String getLexeme() {
		return lexeme;
	}

	public final int getPosition() {
		return position;
	}

	public String toString() {
		return id + " ( " + lexeme + " ) @ " + position;
	}

	public int getLinha() {
		return this.linha;
	}

	public void setLinha(int linha) {
		this.linha = linha;
	}
}
