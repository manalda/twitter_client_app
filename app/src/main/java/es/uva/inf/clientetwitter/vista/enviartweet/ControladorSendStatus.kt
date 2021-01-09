package es.uva.inf.clientetwitter.vista.enviartweet

import es.uva.inf.clientetwitter.cliente.TwitterClient

class ControladorSendStatus(private val vista:SendStatusActivity, private val twitter:TwitterClient) {
    suspend fun sendTweet() {
        val message = when(twitter.sendTweet(vista.getSendingStatus())) {
            0 -> "Tweet enviado correctamente"
            503 -> "Error al enviar el Tweet, servidor saturado"
            401 -> "Acceso denegado"
            else -> "Fallo en el envío del Tweet"
        }

        vista.showResponseMessage(message)
    }
}