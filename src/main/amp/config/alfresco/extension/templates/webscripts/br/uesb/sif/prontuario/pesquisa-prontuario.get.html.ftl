<p>Propriedades do Prontuário da matrícula: ${matricula}</p>
<table border="3">
  <tr><th>Nome</th><th>Propriedades</th><th>Matrícula</th><th>Cargo</th><th>E-mail</th></tr>

  <#assign prontuario_matricula = "{http://www.uesb.br/model/sifuesb/1.0}prontuario_matricula"/>  
  <#assign cargo = "{http://www.uesb.br/model/sifuesb/1.0}cargo"/>  
  <#assign email = "{http://www.uesb.br/model/sifuesb/1.0}email"/>  

  <#list nodes as node>
    <tr>
      <td>${node.name}</td>
      <td>
        <#assign keys = node.properties?keys/>
        <#list keys as k>
          ${k}
        </#list>
     </td>
      <td>
        <#if node.properties[prontuario_matricula]?exists>
          ${node.properties[prontuario_matricula]}
        </#if>
      </td>
      <td>
        <#if node.properties[cargo]?exists>
          ${node.properties[cargo]}
        </#if>
      </td>
      <td>
        <#if node.properties[email]?exists>
          ${node.properties[email]}
        </#if>
      </td>
    </tr>
  </#list>
</table>