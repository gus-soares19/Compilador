/*
 * Autores: Eduarda Engels, Gustavo Felipe Soares e Henrique Petters Ferreira
 */

package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import errors.LexicalError;
import errors.SemanticError;
import errors.SyntaticError;
import models.Lexico;
import models.Semantico;
import models.Sintatico;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.JScrollPane;
import javax.swing.JLabel;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.awt.event.ActionEvent;
import javax.swing.JSplitPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.ScrollPaneConstants;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.SystemColor;
import java.awt.Font;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.InputMap;

@SuppressWarnings("serial")
public class Tela_Compilador extends JFrame {

	// Atributos
	private JPanel contentPane;
	private JTextArea taCodigo;
	private JTextArea taMensagem;
	private JLabel lblPastaArquivo;
	private String textoCopiado = "";
	private static final int MINUMUM_WIDTH = 912;
	private static final int MINIMUM_HEIGHT = 608;
	private KeyStroke ctrlN = KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK);
	private KeyStroke ctrlO = KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK);
	private KeyStroke ctrlC = KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK);
	private KeyStroke ctrlV = KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK);
	private KeyStroke ctrlS = KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK);
	private KeyStroke ctrlX = KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK);
	private KeyStroke f1 = KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0);
	private KeyStroke f7 = KeyStroke.getKeyStroke(KeyEvent.VK_F7, 0);
	private InputMap inputMap = null;
	private String[] linhas = null;

	// Método main
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela_Compilador frame = new Tela_Compilador();
					frame.setMinimumSize(new Dimension(MINUMUM_WIDTH, MINIMUM_HEIGHT));

					// Redimensiona a tela
					frame.addComponentListener(new ComponentAdapter() {
						public void componentResized(ComponentEvent e) {
							Dimension size = frame.getSize();
							Dimension min = frame.getMinimumSize();

							if (size.getWidth() < min.getWidth()) {
								frame.setSize((int) min.getWidth(), (int) size.getHeight());
							}
							if (size.getHeight() < min.getHeight()) {
								frame.setSize((int) size.getWidth(), (int) min.getHeight());
							}
						}
					});

					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Construtor
	public Tela_Compilador() {

		setTitle("Compilador");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, MINUMUM_WIDTH, MINIMUM_HEIGHT);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(211, 211, 211));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JButton btnNovo = new JButton("novo [ctrl + n]");
		btnNovo.setToolTipText("criar arquivo");
		btnNovo.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 14));
		btnNovo.setBackground(SystemColor.controlHighlight);
		Action acNovo = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				// limpa os campos de texto da tela
				limparTela();
			}
		};
		btnNovo.addActionListener(acNovo);
		btnNovo.setFocusable(false);

		JButton btnAbrir = new JButton("abrir [ctrl + o]");
		btnAbrir.setToolTipText("abrir arquivo");
		btnAbrir.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 14));
		Action acAbrirArquivo = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				// abre o arquivo selecionado
				abrirArquivo(selecionarArquivo(false));
			}
		};
		btnAbrir.addActionListener(acAbrirArquivo);
		btnAbrir.setBackground(SystemColor.controlHighlight);
		btnAbrir.setFocusable(false);

		JButton btnSalvar = new JButton("salvar [ctrl + s]");
		btnSalvar.setToolTipText("salvar arquivo");
		btnSalvar.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 14));
		Action acSalvarArquivo = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				// salva o arquivo aberto ou cria um caso não exista
				if (!lblPastaArquivo.getText().isEmpty()) {
					Path caminho = Paths.get(lblPastaArquivo.getText());
					salvarArquivo(caminho.toFile());
				} else {
					salvarArquivo(selecionarArquivo(true));
				}
			}
		};
		btnSalvar.addActionListener(acSalvarArquivo);
		btnSalvar.setBackground(SystemColor.controlHighlight);
		btnSalvar.setFocusable(false);

		JButton btnCopiar = new JButton("copiar [ctrl + c]");
		btnCopiar.setToolTipText("copiar texto");
		btnCopiar.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 14));
		Action acCopiarTexto = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				// realiza a cópia do texto na área de código
				textoCopiado = taCodigo.getSelectedText();
			}
		};
		btnCopiar.addActionListener(acCopiarTexto);
		btnCopiar.setBackground(SystemColor.controlHighlight);
		btnCopiar.setFocusable(false);

		JButton btnColar = new JButton("colar [ctrl + v]");
		btnColar.setToolTipText("colar texto");
		btnColar.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 14));
		Action acColarTexto = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				// cola o texto copiado na área de código
				if (textoCopiado != null) {
					taCodigo.append(textoCopiado);
				}
			}
		};
		btnColar.addActionListener(acColarTexto);
		btnColar.setBackground(SystemColor.controlHighlight);
		btnColar.setFocusable(false);

		JButton btnRecortar = new JButton("recortar [ctrl + x]");
		btnRecortar.setToolTipText("recortar texto");
		btnRecortar.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 14));
		Action acRecortarTexto = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				// recorta o texto da área de código
				textoCopiado = taCodigo.getSelectedText();
				taCodigo.setText("");
			}
		};
		btnRecortar.addActionListener(acRecortarTexto);
		btnRecortar.setBackground(SystemColor.controlHighlight);
		btnRecortar.setFocusable(false);

		JButton btnCompilar = new JButton("compilar [F7]");
		btnCompilar.setToolTipText("compilar c\u00F3digo");
		btnCompilar.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 14));
		Action acCompilar = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				// compila o código gerado

				Lexico lexico = new Lexico();
				Sintatico sintatico = new Sintatico();
				Semantico semantico = new Semantico();

				Reader reader = new StringReader(taCodigo.getText());
				lexico.setInput(reader);
				separarLinhas();

				try {
					sintatico.parse(lexico, semantico); // tradução dirigida pela sintaxe
					taMensagem.setText("programa compilado com sucesso");
					salvarArquivoIl(semantico.getCodigo().toString());
				}

				catch (LexicalError le) {
					String token = getToken(le.getPosition());
					taMensagem.setText(
							"Erro na linha " + getLinhaErro(le.getPosition()) + " - " + token + " " + le.getMessage());
				} catch (SyntaticError se) {
					String token = getToken(se.getPosition());
					taMensagem.setText("Erro na linha " + getLinhaErro(se.getPosition()) + "  - encontrado: " + token
							+ " esperado: " + se.getMessage());
				} catch (SemanticError se) {
					taMensagem.setText(se.getMessage());
				}

			}
		};
		btnCompilar.addActionListener(acCompilar);
		btnCompilar.setBackground(SystemColor.controlHighlight);
		btnCompilar.setFocusable(false);

		JButton btnEquipe = new JButton("equipe [F1]");
		btnEquipe.setToolTipText("mostrar equipe");
		btnEquipe.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 14));
		Action acMostrarEquipe = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				// mostra os membros da equipe na área de mensagens
				mostrarMembros();
			}
		};
		btnEquipe.addActionListener(acMostrarEquipe);
		btnEquipe.setBackground(SystemColor.controlHighlight);
		btnEquipe.setFocusable(false);

		lblPastaArquivo = new JLabel("");
		lblPastaArquivo.setVerticalAlignment(SwingConstants.BOTTOM);
		lblPastaArquivo.setToolTipText("local do arquivo");
		lblPastaArquivo.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 12));

		JSeparator separadorVertical = new JSeparator();
		separadorVertical.setBackground(new Color(169, 169, 169));
		separadorVertical.setForeground(new Color(240, 255, 240));
		separadorVertical.setOrientation(SwingConstants.VERTICAL);

		JSplitPane splitPane = new JSplitPane();
		splitPane.setToolTipText("");
		splitPane.setBackground(new Color(211, 211, 211));
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setDividerLocation(440);
		splitPane.setAutoscrolls(true);

		JScrollPane spCodigo = new JScrollPane();
		spCodigo.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		spCodigo.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		splitPane.setLeftComponent(spCodigo);

		taCodigo = new JTextArea();
		taCodigo.setBorder(new NumberedBorder());
		taCodigo.setToolTipText("\u00E1rea para codificar");
		spCodigo.setViewportView(taCodigo);

		JScrollPane spMensagem = new JScrollPane();
		spMensagem.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		spMensagem.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		splitPane.setRightComponent(spMensagem);

		taMensagem = new JTextArea();
		taMensagem.setToolTipText("\u00E1rea de mensagens");
		taMensagem.setEditable(false);
		spMensagem.setViewportView(taMensagem);

		JSeparator separadorHorizontal = new JSeparator();
		separadorHorizontal.setForeground(new Color(240, 255, 240));
		separadorHorizontal.setBackground(new Color(169, 169, 169));

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(btnColar, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
				.addComponent(btnEquipe, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
				.addComponent(separadorHorizontal, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE)
				.addGroup(gl_contentPane.createSequentialGroup().addGap(10)
						.addComponent(lblPastaArquivo, GroupLayout.DEFAULT_SIZE, 866, Short.MAX_VALUE).addGap(12))
				.addComponent(btnAbrir, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
				.addComponent(btnRecortar, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
				.addComponent(btnCompilar, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
				.addComponent(btnNovo, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
				.addGroup(gl_contentPane.createSequentialGroup().addGap(179).addComponent(separadorVertical,
						GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
				.addComponent(btnSalvar, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
				.addComponent(btnCopiar, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
				.addGroup(gl_contentPane.createSequentialGroup().addGap(193)
						.addComponent(splitPane, GroupLayout.DEFAULT_SIZE, 693, Short.MAX_VALUE).addGap(2)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addGap(240)
						.addComponent(btnColar, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE).addGap(120)
						.addComponent(btnEquipe, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE).addGap(18)
						.addComponent(separadorHorizontal, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addGap(23).addComponent(lblPastaArquivo, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup().addGap(60).addComponent(btnAbrir,
						GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup().addGap(300).addComponent(btnRecortar,
						GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup().addGap(360).addComponent(btnCompilar,
						GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
				.addComponent(btnNovo, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
				.addComponent(separadorVertical, GroupLayout.PREFERRED_SIZE, 499, GroupLayout.PREFERRED_SIZE)
				.addGroup(gl_contentPane.createSequentialGroup().addGap(120).addComponent(btnSalvar,
						GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup().addGap(180).addComponent(btnCopiar,
						GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(splitPane, GroupLayout.DEFAULT_SIZE, 541, Short.MAX_VALUE).addGap(24)));
		contentPane.setLayout(gl_contentPane);

		// Mapeia cada atalho para sua função
		inputMap = rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);

		inputMap.put(ctrlO, "abrirArquivo");
		rootPane.getActionMap().put("abrirArquivo", acAbrirArquivo);

		inputMap.put(ctrlS, "salvarArquivo");
		rootPane.getActionMap().put("salvarArquivo", acSalvarArquivo);

		inputMap.put(ctrlN, "novo");
		rootPane.getActionMap().put("novo", acNovo);

		inputMap.put(ctrlC, "copiarTexto");
		rootPane.getActionMap().put("copiarTexto", acCopiarTexto);

		inputMap.put(ctrlV, "colarTexto");
		rootPane.getActionMap().put("colarTexto", acColarTexto);

		inputMap.put(ctrlX, "recortarTexto");
		rootPane.getActionMap().put("recortarTexto", acRecortarTexto);

		inputMap.put(f1, "mostrarEquipe");
		rootPane.getActionMap().put("mostrarEquipe", acMostrarEquipe);

		inputMap.put(f7, "compilar");
		rootPane.getActionMap().put("compilar", acCompilar);
	}

	// Métodos

	// salva no arquivo passado como parâmetro o texto da área de código
	private void salvarArquivo(File arquivo) {
		if (arquivo != null) {
			try {

				FileWriter fw = new FileWriter(arquivo);
				BufferedWriter bw = new BufferedWriter(fw);

				bw.write(taCodigo.getText());
				bw.close();
				fw.close();

				taMensagem.setText("");

			} catch (IOException ioe) {
				taMensagem.setText("Erro! Falha ao abrir ou fechar o arquivo.\n (" + ioe.getMessage() + ")");
			}
		}
	}

	private void salvarArquivoIl(String codigoIl) {
		File arquivo = new File("teste.il");
		try {

			FileWriter fw = new FileWriter(arquivo);
			BufferedWriter bw = new BufferedWriter(fw);

			bw.write(codigoIl);
			bw.close();
			fw.close();

			taMensagem.append(
					"\nArquivo IL gerado no diretório do programa. Nome do arquivo: teste.il");

		} catch (IOException ioe) {
			taMensagem.setText("Erro! Falha ao abrir ou fechar o arquivo.\n (" + ioe.getMessage() + ")");
		}

	}

	private String getToken(int posicaoAtual) {

		if (posicaoAtual >= taCodigo.getText().length()) {
			posicaoAtual--;
		}
		char c = taCodigo.getText().charAt(posicaoAtual);
		String token = "";

		// verifica se é letra ou número
		while (Character.isLetter(c) || Character.isDigit(c)) {
			token += String.valueOf(taCodigo.getText().charAt(posicaoAtual));
			posicaoAtual++;

			if (posicaoAtual >= taCodigo.getText().length()) {
				break;
			}

			c = taCodigo.getText().charAt(posicaoAtual);
		}

		if (token.isEmpty()) {

			// verifica se é um caractere especial
			while (!Character.isLetter(c) && !Character.isDigit(c)) {

				if ((int) c == (int) 10) {
					break;
				} else {
					token += String.valueOf(taCodigo.getText().charAt(posicaoAtual));
					posicaoAtual++;
				}

				if (posicaoAtual >= taCodigo.getText().length()) {
					break;
				}
				c = taCodigo.getText().charAt(posicaoAtual);

			}
		}

		if (token.isEmpty()) {
			token += "EOF";
		}

		return token;
	}

	private void separarLinhas() {
		linhas = taCodigo.getText().split("\\n");
	}

	private int getLinhaErro(int posicao) {

		int tamanho = 0;

		for (int i = 0; i < linhas.length; i++) {
			tamanho += linhas[i].length() + 1; // o +1 serve para considerar a quebra de linha (\n).
			if (tamanho - posicao > 0) {
				return i + 1;
			}
		}

		return -1;
	}

	// abre o arquivo passado como parâmetro e exibe o texto na área de código
	public void abrirArquivo(File arquivo) {
		if (arquivo != null) {
			try {

				int c = 0;
				char c2 = 0;
				FileReader reader = new FileReader(arquivo);

				while ((c = reader.read()) != -1) { // -1 indica o fim do arquivo
					c2 = (char) c;
					taCodigo.append(String.valueOf(c2));
				}

				reader.close();

			} catch (FileNotFoundException fnfe) {
				taMensagem.setText("Erro! Arquivo não encontrado.\n (" + fnfe.getMessage() + ")");
			} catch (IOException ioe) {
				taMensagem.setText("Erro! Não foi possível abrir ou fechar o arquivo.\n (" + ioe.getMessage() + ")");
			}
		}
	}

	// seleciona um arquivo
	private File selecionarArquivo(boolean isArqNovo) {

		File arquivo = null;
		JFileChooser jfc = new JFileChooser();
		FileNameExtensionFilter extensoes = new FileNameExtensionFilter("txt", "txt");
		jfc.setFileFilter(extensoes);
		jfc.setAcceptAllFileFilterUsed(false);
		int retorno = jfc.showOpenDialog(null);

		if (retorno == JFileChooser.APPROVE_OPTION) {
			arquivo = jfc.getSelectedFile();
			lblPastaArquivo.setText(arquivo.getAbsolutePath());
			taMensagem.setText("");
			if (!isArqNovo) {
				taCodigo.setText("");
			}

		}

		return arquivo;
	}

	// limpa os campos de texto da tela
	private void limparTela() {
		taCodigo.setText("");
		taMensagem.setText("");
		lblPastaArquivo.setText("");
	}

	// exibe os membros da equipe na área de mensagens
	private void mostrarMembros() {
		taMensagem.setText(
				"Membros da equipe:\n1 - Eduarda Engels;\n2 - Gustavo Felipe Soares;\n3 - Henrique Petters Ferreira.");
	}

}
