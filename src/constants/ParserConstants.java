package constants;

public interface ParserConstants
{
    int START_SYMBOL = 39;

    int FIRST_NON_TERMINAL    = 39;
    int FIRST_SEMANTIC_ACTION = 70;

    int[][] PARSER_TABLE =
    {
        { -1, -1, -1, -1, -1, -1,  0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1,  1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,  2, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, 12, -1, -1, -1, -1, -1, -1, -1, -1, 12, 12, 12, -1, 12, 12, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, 13, -1, -1, -1, -1, -1, 14, 14, -1, 13, 13, 13, -1, 13, 13, -1, 14, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1,  6,  7,  8,  9, -1, -1, -1, 11, -1, -1, -1, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1,  3, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,  5,  4, -1, -1, -1, -1, -1, -1,  5, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, 15, -1, -1, -1, -1, -1, -1, -1, -1, 18, 16, 17, -1, 19, 19, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, 20, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 21, -1, -1, 22, 22, 22, 22, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, 41, 41, 41, 41, 41, -1, -1, -1, 41, -1, -1, -1, 41, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 41, -1, -1, -1, 41, -1, -1, -1, -1, 41, 41, -1, -1 },
        { -1, 44, -1, -1, -1, -1, -1, 44, 44, -1, 44, 44, 44, -1, 44, 44, -1, 44, -1, 44, 44, -1, -1, -1, -1, -1, 44, 42, 43, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 29, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 32, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 36, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 40, 39, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, 28, -1, -1, -1, -1, -1, 28, 28, -1, 28, 28, 28, -1, 28, 28, -1, 28, -1, -1, 27, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 23, 24, 25, 26, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, 31, -1, -1, -1, 30, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, 33, 33, 33, 33, 33, -1, -1, -1, 33, -1, -1, -1, 33, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 33, -1, -1, -1, 33, -1, -1, -1, -1, 33, 33, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 34, -1, -1, -1, -1, -1, -1, 35, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, 37, 38, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
        { -1, 45, 45, 45, 45, 45, -1, -1, -1, 47, -1, -1, -1, 46, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 45, -1, -1, -1, 48, -1, -1, -1, -1, 45, 45, -1, -1 },
        { -1, 49, 49, 49, 49, 49, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 49, -1, -1, -1, -1, -1, -1, -1, -1, 49, 49, -1, -1 },
        { -1, 51, -1, -1, -1, -1, -1, 51, 51, -1, 51, 51, 51, -1, 51, 51, -1, 51, -1, 51, 51, -1, -1, -1, -1, -1, 51, 51, 51, -1, 50, 50, 50, 50, -1, -1, -1, -1 },
        { -1, 56, 56, 56, 56, 56, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 56, -1, -1, -1, -1, -1, -1, -1, -1, 56, 56, -1, -1 },
        { -1, 59, -1, -1, -1, -1, -1, 59, 59, -1, 59, 59, 59, -1, 59, 59, -1, 59, -1, 59, 59, -1, -1, -1, -1, -1, 59, 59, 59, -1, 59, 59, 59, 59, 57, 58, -1, -1 },
        { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 52, 53, 54, 55, -1, -1, -1, -1 },
        { -1, 64, 65, 66, 67, 68, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 69, -1, -1, -1, -1, -1, -1, -1, -1, 70, 71, -1, -1 },
        { -1, 60, 60, 60, 60, 60, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 60, -1, -1, -1, -1, -1, -1, -1, -1, 60, 60, -1, -1 },
        { -1, 63, -1, -1, -1, -1, -1, 63, 63, -1, 63, 63, 63, -1, 63, 63, -1, 63, -1, 63, 63, -1, -1, -1, -1, -1, 63, 63, 63, -1, 63, 63, 63, 63, 63, 63, 61, 62 }
    };

    int[][] PRODUCTIONS = 
    {
        {  85,   7,  17,  40,  18,  17,  41,  18,  86 },
        {  44,  19,  43,  91,  40 },
        {   0 },
        {   2,  95,  45 },
        {  20,  44 },
        {   0 },
        {   3 },
        {   4 },
        {   5 },
        {   6 },
        {  14 },
        {  10 },
        {  46,  42 },
        {  41 },
        {   0 },
        {  47 },
        {  51 },
        {  52 },
        {  53 },
        {  54 },
        {   2,  92,  48 },
        {  19,  49,  93,  55 },
        {  56,  49 },
        {  22 },
        {  23 },
        {  24 },
        {  25 },
        {  21,  11,  49 },
        {   0 },
        {  12,  26,  57,  44,  94,  27 },
        {   6,  87,  84,  20 },
        {   0 },
        {  13,  26,  58,  27 },
        {  49,  84,  59 },
        {  20,  49,  84,  59 },
        {   0 },
        {  11,  49,  96,  41,  60,   9,  98 },
        {  97,   8,  41 },
        {   0 },
        {  16,  99,  49, 100,  41,   9, 101 },
        {  15,  99,  49, 102,  41,   9, 101 },
        {  61,  50 },
        {  28,  61,  88,  50 },
        {  29,  61,  89,  50 },
        {   0 },
        {  62 },
        {  14,  81 },
        {  10,  82 },
        {  30,  61,  83 },
        {  64,  63 },
        {  66,  79,  64,  80 },
        {   0 },
        {  31 },
        {  32 },
        {  33 },
        {  34 },
        {  68,  65 },
        {  35,  68,  71,  65 },
        {  36,  68,  72,  65 },
        {   0 },
        {  67,  69 },
        {  37,  67,  73,  69 },
        {  38,  67,  74,  69 },
        {   0 },
        {   2, 103 },
        {   3,  75 },
        {   4,  76 },
        {   5,  90 },
        {   6,  87 },
        {  26,  49,  27 },
        {  35,  67,  77 },
        {  36,  67,  78 }
    };

    String[] PARSER_ERROR =
    {
        "",
        "EOF",
//        "fim de programa",
        "identificador",
        "int",
        "float",
        "char",
        "str",
        "def",
        "else",
        "end",
        "false",
        "if",
        "input",
        "output",
        "true",
        "until",
        "while",
        "{%",
//        "smb_abre_chaves",
        "%}",
//        "smb_fecha_chaves",
        "=",
//        "smb_igual",
        ",",
//        "smb_virgula",
        ":",
//        "smb_2pontos",
        "+=",
//        "smb_soma",
        "-=",
//        "smb_subtracao",
        "*=",
//        "smb_multiplicacao",
        "/=",
//        "smb_divisao",
        "(",
//        "smb_abre_parent",
        ")",
//        "smb_fecha_parent",
        "&",
//        "smb_e_comercial",
        "|",
//        "smb_pipe",
        "!",
//        "smb_exclamacao",
        "==",
//        "smb_igualdade",
        "!=",
//        "smb_diferenca",
        "<",
//        "smb_menor",
        ">",
//        "smb_maior",
        "+",
//        "smb_mais",
        "-",
//        "smb_menos",
        "*",
//        "smb_vezes",
        "/",
//        "smb_barra",
        "def",
//        "<programa> inv?lido",
        "identificador   %}",
//        "<declaracao_de_variaveis> inv?lido",
        "identificador   if   input   output   until   while %}",
//        "<lista_de_comandos> inv?lido",
        "identificador   else   end   if   input   output   until   while   }",
//        "<lista_de_comandos1> inv?lido",
        "constante",
//      "<valor> inv?lido",
        "identificador",
//        "<lista_de_identificadores> inv?lido",
        "=   ,   )",
//        "<lista_de_identificadores1> inv?lido",
        "identificador   if   input   output   until   while",
//        "<comando> inv?lido",
        "identificador",
//        "<atribuicao> inv?lido",
        "=   +=   -=   *=   /=",
//        "<atribuicao1> inv?lido",
        "express?o",
//        "<expressao> inv?lido",
        "express?o",
//        "<expressao1> inv?lido",
        "input",
//        "<input> inv?lido",
        "output",
//        "<output> inv?lido",
        "if",
//        "<selecao> inv?lido",
        "until   while",
//        "<repeticao> inv?lido",
        "identificador   else   end   if   input   output   until   while    %}   :",
//        "<sufixo> inv?lido",
        "+=   -=   *=   /=",
//        "<sinal> inv?lido",
        "identificador   constante_str",
//        "<str_opcional> inv?lido",
        "express?o",
//        "<lista_de_expressoes> inv?lido",
        ",   )",
//        "<lista_de_expressoes1> inv?lido",
        "else   end",
//        "<else_opcional> inv?lido",
        "express?o",
//        "<elemento> inv?lido",
        "express?o",
//        "<relacional> inv?lido",
        "express?o",
//        "<relacional1> inv?lido",
        "express?o",
//        "<aritmetica> inv?lido",
        "express?o",
//        "<aritimetica1> inv?lido",
        "express?o",
//        "<operador_relacional> inv?lido",
        "express?o",
//        "<fator> inv?lido",
        "express?o",
//        "<termo> inv?lido",
        "express?o",
//        "<termo1> inv?lido"
    };
}
