package `in`.mywebdomain.multiassign.model

data class JokesAuto(
    val type: String,
    val value: List<Jokes>
)

data class Jokes(
    val categories: List<String>,
    val id: Int,
    val joke: String
)