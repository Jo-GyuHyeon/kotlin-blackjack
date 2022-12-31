package blackjack.view

object InputView {
    private val DELIMITER = Regex(",")
    private const val YES = "Y"
    private const val NO = "N"

    fun requestPositiveNumber(): Int {
        val input: String = requestString()
        val number = input.toIntOrNull()

        require(number != null) { "숫자를 입력 해주세요." }
        require(number > 0) { "양수를 입력 해주세요." }

        return number
    }

    fun requestStringList(): List<String> =
        requestString()
            .split(DELIMITER)
            .map { it.trim() }

    fun requestConfirm(): Boolean {
        val input = requestString()
        val isYes = input.uppercase() == YES
        val isNo = input.uppercase() == NO

        require(isYes || isNo) { "예는 y, 아니오는 n 로 입력해주세요." }

        return isYes
    }

    private fun requestString(): String {
        val input = readlnOrNull()
        require(!input.isNullOrBlank()) { "빈값을 입력 할 수 없습니다." }

        return input
    }
}
