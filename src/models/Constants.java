package models;

import constants.ParserConstants;
import constants.ScannerConstants;

public interface Constants extends ScannerConstants, ParserConstants
{
    int EPSILON  = 0;
    int DOLLAR   = 1;

    int t_identificador = 2;
    int t_int = 3;
    int t_float = 4;
    int t_char = 5;
    int t_str = 6;
    int t_def = 7;
    int t_else = 8;
    int t_end = 9;
    int t_false = 10;
    int t_if = 11;
    int t_input = 12;
    int t_output = 13;
    int t_true = 14;
    int t_until = 15;
    int t_while = 16;
    int t_smb_abre_chaves = 17;
    int t_smb_fecha_chaves = 18;
    int t_smb_igual = 19;
    int t_smb_virgula = 20;
    int t_smb_2pontos = 21;
    int t_smb_soma = 22;
    int t_smb_subtracao = 23;
    int t_smb_multiplicacao = 24;
    int t_smb_divisao = 25;
    int t_smb_abre_parent = 26;
    int t_smb_fecha_parent = 27;
    int t_smb_e_comercial = 28;
    int t_smb_pipe = 29;
    int t_smb_exclamacao = 30;
    int t_smb_igualdade = 31;
    int t_smb_diferenca = 32;
    int t_smb_menor = 33;
    int t_smb_maior = 34;
    int t_smb_mais = 35;
    int t_smb_menos = 36;
    int t_smb_vezes = 37;
    int t_smb_barra = 38;

}
