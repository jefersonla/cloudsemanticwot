/**
 * Copyright 2016 Nailton Andrade.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 **/

module.exports = function (RED) {
    "use strict";
    // Trabalhar com jquery dentro do javascript
    // var jsdom = require("jsdom");
    // var window = jsdom.jsdom().defaultView;
    // var $ = require('jquery')(window);
    // AQUI IMPORTA AS BIBLIOTECAS, INCLUSO A DO SPARQL CLIENTE

    function SPARQL(config) {
        // AQUI ELE CHAMA ASSIM QUE O NODE É INICIADO OU 
        // QUANDO O NODE É COLOCADO NO DASHBOARD
        // Criando o node
        RED.nodes.createNode(this, config);

        // AQUI VC PEGA AS VARIAVES DO CONTEXTO THIS, E COPIA PARA O NODE
        // Copiando dados do config
        
        // tentando tirar os \n
        var someText = config.query.replace(/(\r\n|\n|\r)/gm," ");
        this.query = someText.split("\n").join(" ");
        var node = this;

        console.log(config);

        // Esse node vai basicamente enviar a query para a triplestore do tipo input
        this.on('input', function (msg) {

            console.log(node);

            msg.payload = node.query;
            msg.topic = "sparql";
            // Manda a mensagem para o proximo node
            node.send(msg);
        });

        // ESSA FUNÇÃO É CHAMADA QUANDO O NODE-RED É FINALIZADO
        this.on("close", function () {
            // Called when the node is shutdown - eg on redeploy.
            // Allows ports to be closed, connections dropped etc.
            // eg: node.client.disconnect();
        });

    }
    // Registra a função Discovery no node-red
    RED.nodes.registerType("SPARQL", SPARQL);
}
