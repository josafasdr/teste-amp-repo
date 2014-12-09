/**
 *
 */

var matricula;

if (url.templateArgs.matricula !== null) {
	matricula = url.templateArgs.matricula;
} else {
	status.code = 400;
	status.message = "Um número de matrícula precisa ser inserido!"
	status.redirect = true;
}

var pesquisa = "@sif\\:lista_prontuario_matricula:" + matricula;
var resultado = search.luceneSearch(pesquisa);

if (resultado.length != 0) {
	model.prontuario = resultado[0];
}
