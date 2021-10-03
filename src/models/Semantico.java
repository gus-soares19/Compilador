/*
 * Autores: Eduarda Engels, Gustavo Felipe Soares e Henrique Petters Ferreira
 */

package models;

import java.util.ArrayList;
import java.util.HashMap;

import errors.SemanticError;

public class Semantico implements Constants {

	private ArrayList<String> pilha = new ArrayList<>();
	private ArrayList<String> identificadores = new ArrayList<>();
	private ArrayList<String> pilhaRotulos = new ArrayList<>();
	private StringBuilder codigo = new StringBuilder();
	private String operador;
	private Token identificador;
	private HashMap<String, String> tabelaSimbolos = new HashMap<>();
	private String rotulo;
	private int indice = 1;

	public void executeAction(int action, Token token) throws SemanticError {

		switch (action) {
		case 1:
			acao1();
			break;
		case 2:
			acao2();
			break;

		case 3:
			acao3();
			break;

		case 4:
			acao4();
			break;
		case 5:
			acao5(token);
			break;

		case 6:
			acao6(token);
			break;
		case 7:
			acao7();
			break;

		case 8:
			acao8();
			break;

		case 9:
			acao9(token);
			break;
		case 10:
			acao10();
			break;

		case 11:
			acao11();
			break;
		case 12:
			acao12();
			break;

		case 13:
			acao13();
			break;

		case 14:
			acao14();
			break;
		case 15:
			acao15();
			break;

		case 16:
			acao16();
			break;
			
		case 17:
			acao17(token);
			break;

		case 18:
			acao18();
			break;

		case 19:
			acao19();
			break;

		case 20:
			acao20(token);
			break;

		case 21:
			acao21(token);
			break;
		case 22:
			acao22(token);
			break;

		case 23:
			acao23();
			break;

		case 24:
			acao24();
			break;
		case 25:
			acao25(token);
			break;

		case 26:
			acao26();
			break;

		case 27:
			acao27();
			break;

		case 28:
			acao28();
			break;

		case 29:
			acao29();
			break;

		case 30:
			acao30();
			break;

		case 31:
			acao31();
			break;

		case 32:
			acao32();
			break;

		case 33:
			acao33(token);
			break;

		default:
			throw new SemanticError("Ação semântica não implementada: " + action);
		}
	}

	private void acao1() {
		String tipo1 = this.desempilhar();
		String tipo2 = this.desempilhar();

		if (tipo1.equals("float64") || tipo2.equals("float64")) {
			this.empilhar("float64");
		} else {
			this.empilhar("int64");
		}
		this.adicionarCodigo("add\n");
	}

	private void acao2() {
		String tipo1 = this.desempilhar();
		String tipo2 = this.desempilhar();

		if (tipo1.equals("float64") || tipo2.equals("float64")) {
			this.empilhar("float64");
		} else {
			this.empilhar("int64");
		}
		this.adicionarCodigo("sub\n");
	}

	private void acao3() {
		String tipo1 = this.desempilhar();
		String tipo2 = this.desempilhar();

		if (tipo1.equals("float64") || tipo2.equals("float64")) {
			this.empilhar("float64");
		} else {
			this.empilhar("int64");
		}
		this.adicionarCodigo("mul\n");
	}

	private void acao4() throws SemanticError {
		String tipo1 = this.desempilhar();
		String tipo2 = this.desempilhar();

		if (!tipo1.equals(tipo2)) {
			throw new SemanticError("incompatíveis acao4");
		}

		this.empilhar(tipo1);
		this.adicionarCodigo("div\n");
	}

	private void acao5(Token token) {
		this.empilhar("int64");
		this.adicionarCodigo("ldc.i8 " + token.getLexeme().replace("_", "") + "\n");
		this.adicionarCodigo("conv.r8\n");
	}

	private void acao6(Token token) {
		this.empilhar("float64");
		this.adicionarCodigo("ldc.r8 " + token.getLexeme().replace("_", "") + "\n");
	}

	private void acao7() throws SemanticError {
		String tipo = this.desempilhar();

		if (!tipo.equals("float64") && !tipo.equals("int64")) {
			throw new SemanticError("tipos incompatíveis acao7");
		}

		this.empilhar(tipo);
	}

	private void acao8() throws SemanticError {
		String tipo = this.desempilhar();

		if (!tipo.equals("float64") && !tipo.equals("int64")) {
			throw new SemanticError("tipos incompatíveis acao8");
		}

		this.adicionarCodigo("ldc.i8 -1\n");
		this.adicionarCodigo("conv.r8\n");
		this.adicionarCodigo("mul\n");
		this.empilhar(tipo);
	}

	private void acao9(Token token) {
		this.setOperador(token.getLexeme());
	}

	private void acao10() throws SemanticError {
		String tipo1 = this.desempilhar();
		String tipo2 = this.desempilhar();

		if ((tipo1.equals("int64") || tipo1.equals("float64")) && (tipo2.equals("int64") || tipo2.equals("float64"))) {
			this.empilhar("bool");
		} else {
			if (tipo1.equals("string") && tipo2.equals("string")) {
				this.empilhar("bool");
				this.adicionarCodigo("ceq\n");
			} else {
				throw new SemanticError("tipos incompatíveis acao10");
			}
		}

		switch (this.getOperador()) {
		case "==":
			this.adicionarCodigo("ceq\n");
			break;

		case "!=":
			this.adicionarCodigo("xor ceq\n");
			break;

		case ">":
			this.adicionarCodigo("cgt\n");
			break;

		case "<":
			this.adicionarCodigo("clt\n");
			break;
		}
	}

	private void acao11() {
		this.empilhar("bool");
		this.adicionarCodigo("ldc.i4.1\n");
	}

	private void acao12() {
		this.empilhar("bool");
		this.adicionarCodigo("ldc.i4.0\n");
	}

	private void acao13() throws SemanticError {
		String tipo = this.desempilhar();

		if (!tipo.equals("bool")) {
			throw new SemanticError("tipo esperado: bool");
		}

		this.empilhar("bool");
		this.adicionarCodigo("ldc.i4.1\n");
		this.adicionarCodigo("xor\n");
	}

	private void acao14() {
		String tipo = this.desempilhar();

		switch (tipo) {
		case "int64":
			this.adicionarCodigo("conv.i8\n");
			this.adicionarCodigo("call void [mscorlib]System.Console::Write(" + tipo + ")\n");
			break;

		case "char":
			this.adicionarCodigo("call void [mscorlib]System.Console::Write(string)\n");
			break;

		default:
			this.adicionarCodigo("call void [mscorlib]System.Console::Write(" + tipo + ")\n");
			break;
		}

	}

	private void acao15() {
		this.adicionarCodigo(
				".assembly extern mscorlib {} .assembly _codigo_objeto{} .module   _codigo_objeto.exe .class public _UNICA{  .method static public void _principal() { .entrypoint\n");
	}

	private void acao16() {
		this.adicionarCodigo("ret } }");
	}

	private void acao17(Token token) {
		this.empilhar("string");
		this.adicionarCodigo("ldstr \"" + token.getLexeme().replaceAll("'", "") + "\"\n");
	}

	private void acao18() throws SemanticError {
		String tipo1 = this.desempilhar();
		String tipo2 = this.desempilhar();

		if (!tipo1.equals("bool") || !tipo2.equals("bool")) {
			throw new SemanticError("tipo esperado: bool");
		}

		this.adicionarCodigo("and\n");
	}

	private void acao19() throws SemanticError {
		String tipo1 = this.desempilhar();
		String tipo2 = this.desempilhar();

		if (!tipo1.equals("bool") || !tipo2.equals("bool")) {
			throw new SemanticError("tipo esperado: bool");
		}

		this.adicionarCodigo("or\n");
	}

	private void acao20(Token token) {
		this.empilhar("string");

		switch (token.getLexeme()) {

		case "\\n":
			this.adicionarCodigo("ldstr \"\\n\"\n");
			break;

		case "\\s":
			this.adicionarCodigo("ldstr \" \"\n");
			break;

		case "\\t":
			this.adicionarCodigo("ldstr \"\\t\"\n");
			break;
		}
	}

	private void acao21(Token token) throws SemanticError {
		String lexema = token.getLexeme();
		String tipo = getTipoToken(token);

		for (String string : this.getIdentificadores()) {
			if (tabelaSimbolos.containsKey(string)) {
				throw new SemanticError(token.getLexeme() + " já declarado.");
			}

			this.getTabelaSimbolos().put(string, tipo);
			this.adicionarCodigo(".locals (" + tipo + " " + string + ")\n");

			switch (tipo) {
			case "int64":
				this.adicionarCodigo("ldc.i8 " + lexema.replace("_", "") + "\n");
				break;

			case "float64":
				this.adicionarCodigo("ldc.r8 " + lexema.replace("_", "") + "\n");
				break;

			case "char":

				switch (lexema) {

				case "\\n":
					this.adicionarCodigo("ldstr \"\\n\"\n");
					break;

				case "\\s":
					this.adicionarCodigo("ldstr \" \"\n");
					break;

				case "\\t":
					this.adicionarCodigo("ldstr \"\\t\"\n");
					break;
				}
				break;

			case "string":
				this.adicionarCodigo("ldstr " + lexema.replaceAll("'", "") + "\n");
				break;

			case "bool":
				if (lexema.equals("true")) {
					this.adicionarCodigo("ldc.i4.1\n");
				} else {
					this.adicionarCodigo("ldc.i4.0\n");
				}
				break;
			}
			this.adicionarCodigo("stloc " + string + "\n");
		}

		this.getIdentificadores().clear();
	}

	private void acao22(Token token) throws SemanticError {
		if (!this.getTabelaSimbolos().containsKey(token.getLexeme())) {
			throw new SemanticError(token.getLexeme() + " não declarado.");
		}
		this.setIdentificador(token);
	}

	private void acao23() {
		String tipo = this.getTabelaSimbolos().get(getIdentificador().getLexeme());

		switch (tipo) {
		case "int64":
			this.adicionarCodigo("conv.i8\n");
			this.adicionarCodigo("stloc " + getIdentificador().getLexeme() + "\n");
			break;

		default:
			this.adicionarCodigo("stloc " + getIdentificador().getLexeme() + "\n");
			break;
		}
	}

	private void acao24() throws SemanticError {
		for (String identificador : this.getIdentificadores()) {
			if (!this.getTabelaSimbolos().containsKey(identificador)) {
				throw new SemanticError(identificador + " não declarado.");
			}

			String tipo = this.getTabelaSimbolos().get(identificador);

			switch (tipo) {
			case "int64":
				this.adicionarCodigo("call string [mscorlib]System.Console::ReadLine()\n");
				this.adicionarCodigo("call int64 [mscorlib]System.Int64::Parse(string)\n");
				break;

			case "float64":
				this.adicionarCodigo("call string [mscorlib]System.Console::ReadLine()\n");
				this.adicionarCodigo("call float64 [mscorlib]System.Double::Parse(string)\n");
				break;

			case "bool":
				this.adicionarCodigo("call string [mscorlib]System.Console::ReadLine()\n");
				this.adicionarCodigo("call bool [mscorlib]System.Boolean::Parse(string)\n");
				break;

			case "string":
				this.adicionarCodigo("call string [mscorlib]System.Console::ReadLine()\n");
				break;
			}
			this.adicionarCodigo("stloc " + identificador + "\n");
		}
	}

	private void acao25(Token token) {
		this.getIdentificadores().add(token.getLexeme());
	}

	private void acao26() {
		this.desempilhar();
		this.setRotulo("r" + indice++);
		this.getPilhaRotulos().add(String.valueOf(this.getRotulo()));
		this.adicionarCodigo("brfalse " + this.getRotulo() + "\n");
	}

	private void acao27() {
		this.setRotulo("r" + indice++);
		this.getPilhaRotulos().add(String.valueOf(this.getRotulo()));
		this.adicionarCodigo("br " + this.getRotulo() + "\n");
		this.adicionarCodigo(this.getPilhaRotulos().remove(0) + ":\n");
	}

	private void acao28() {
		this.adicionarCodigo(this.getPilhaRotulos().remove(0) + ":\n");
	}

	private void acao29() {
		this.setRotulo("r" + indice++);
		this.getPilhaRotulos().add(String.valueOf(this.getRotulo()));
		this.adicionarCodigo(this.getPilhaRotulos().get(0) + ":\n");
	}

	private void acao30() {
		this.desempilhar();
		this.setRotulo("r" + indice++);
		this.getPilhaRotulos().add(String.valueOf(this.getRotulo()));
		this.adicionarCodigo("brfalse " + this.getRotulo() + "\n");
	}

	private void acao31() {
		this.adicionarCodigo("br " + this.getPilhaRotulos().remove(0) + "\n");
		this.adicionarCodigo(this.getPilhaRotulos().remove(0) + ":\n");
	}

	private void acao32() {
		this.desempilhar();
		this.setRotulo("r" + indice++);
		this.getPilhaRotulos().add(String.valueOf(this.getRotulo()));
		this.adicionarCodigo("brtrue " + this.getRotulo() + "\n");
	}

	private void acao33(Token token) throws SemanticError {
		if (!this.getTabelaSimbolos().containsKey(token.getLexeme())) {
			throw new SemanticError(token.getLexeme() + " não declarado.");
		}

		this.adicionarCodigo("ldloc " + token.getLexeme() + "\n");

		String tipo = this.getTabelaSimbolos().get(token.getLexeme());

		this.empilhar(tipo);

		if (tipo.equals("int64")) {
			this.adicionarCodigo("conv.r8\n");
		}
	}

	private String getTipoToken(Token token) {
		switch (token.getId()) {
		case 3:
			return "int64";

		case 4:
			return "float64";

		case 5:
			return "char";

		case 6:
			return "string";

		case 10:
			return "bool";

		case 14:
			return "bool";
		}
		return null;
	}

	public void empilhar(String string) {
		this.getPilha().add(string);
	}

	public String desempilhar() {
		return this.getPilha().remove(this.getPilha().size()-1);
	}

	public void adicionarCodigo(String codigo) {
		this.getCodigo().append(codigo);
	}

	public ArrayList<String> getPilha() {
		return pilha;
	}

	public void setPilha(ArrayList<String> pilha) {
		this.pilha = pilha;
	}

	public StringBuilder getCodigo() {
		return codigo;
	}

	public void setCodigo(StringBuilder codigo) {
		this.codigo = codigo;
	}

	public String getOperador() {
		return operador;
	}

	public void setOperador(String operador) {
		this.operador = operador;
	}

	public ArrayList<String> getIdentificadores() {
		return identificadores;
	}

	public void setIdentificadores(ArrayList<String> identificadores) {
		this.identificadores = identificadores;
	}

	public ArrayList<String> getPilhaRotulos() {
		return pilhaRotulos;
	}

	public void setPilhaRotulos(ArrayList<String> pilhaRotulos) {
		this.pilhaRotulos = pilhaRotulos;
	}

	public Token getIdentificador() {
		return identificador;
	}

	public void setIdentificador(Token identificador) {
		this.identificador = identificador;
	}

	public HashMap<String, String> getTabelaSimbolos() {
		return tabelaSimbolos;
	}

	public void setTabelaSimbolos(HashMap<String, String> tabelaSimbolos) {
		this.tabelaSimbolos = tabelaSimbolos;
	}

	public String getRotulo() {
		return rotulo;
	}

	public void setRotulo(String rotulo) {
		this.rotulo = rotulo;
	}
}
