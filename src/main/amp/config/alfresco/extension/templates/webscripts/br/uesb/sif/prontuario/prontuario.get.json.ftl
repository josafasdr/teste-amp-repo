<#if prontuario??>
  {
    "matricula":"${prontuario.properties["sif:lista_prontuario_matricula"]}",
    "nome": "${prontuario.properties["cm:name"]}",
    "cargo": "${prontuario.properties["sif:lista_prontuario_cargo"]}",
    "email": "${prontuario.properties["sif:lista_prontuario_email"]}",
    "protocolo": "${prontuario.properties["sif:lista_prontuario_protocolo"]}",
    "modulo": "${prontuario.properties["sif:lista_prontuario_modulo"]}",
    "prateleira": "${prontuario.properties["sif:lista_prontuario_prateleira"]}",
    "ordem": "${prontuario.properties["sif:lista_prontuario_ordem"]}",
    "tipoPasta": "${prontuario.properties["sif:lista_prontuario_tipo_pasta"]}",
    "quantidadePastas": "${prontuario.properties["sif:lista_prontuario_quantidade_pastas"]}",
    "paginas": "${prontuario.properties["sif:lista_prontuario_paginas"]}",
  }
<#else>
 {}
</#if>
