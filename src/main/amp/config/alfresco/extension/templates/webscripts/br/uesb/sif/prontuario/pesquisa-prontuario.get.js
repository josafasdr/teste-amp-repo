const DEFAULT_MAX_RESULTS = 500;
const SITES_SPACE_QNAME_PATH = "/app:company_home/st:sites/cm:sif";

//var matricula;

function doSearch(matricula, maxResults)
{
    var alfQuery =
    	'PATH:"' + SITES_SPACE_QNAME_PATH + '/cm:documentLibrary//*"' +
        ' AND TYPE:"{http://www.uesb.br/model/sifuesb/1.0}prontuario"' +
        ' AND (sif:prontuario_matricula:' + matricula + ')';

    var queryDef = {
        query: alfQuery,
        language: "fts-alfresco",
        page: {maxItems: maxResults},
        templates: []
    };

    return search.query(queryDef);
}

function main()
{
    var matricula = url.templateArgs.matricula;
    var maxResults = (args.maxResults !== null) ? parseInt(args.maxResults) : DEFAULT_MAX_RESULTS; 

    var nodes = doSearch(matricula, maxResults);

    model.nodes = nodes;
    model.matricula = matricula;
}

main();

// Pesquisas:
// ASPECT:"exif:exif" AND PATH:"/app:company_home/st:sites/cm:sif/cm:documentLibrary//*" AND NOT TYPE:"{http://www.alfresco.org/model/content/1.0}thumbnail" AND NOT TYPE:"{http://www.alfresco.org/model/content/1.0}folder"
// PATH:"/app:company_home/st:sites/cm:sif/cm:documentLibrary//*" AND TYPE:"sif:prontuario" AND (@sif\:prontuario_matricula:'720000002')
// PATH:"/app:company_home/st:sites/cm:sif/cm:documentLibrary//*" AND PARENT:"workspace://SpacesStore/dead763e-4a8f-434b-990a-44eb1d27fbd1"
