import kotlin.random.Random

class Lotto {

    var personList: ArrayList<Person> =
        ArrayList() // определите поле, в котром будут храниться добавленные игроки `Person`
    var thrownNumbers: Set<Int> = setOf() // поле thrownNumbers должно хранить в себе набор выброшенных чисел.

    // определите подходящую структуру данных

    fun addPerson(person: Person) {
        personList.add(person) // добавить игрока в список игроков
    }

    fun start() {
        var ind: Int = 100
        val randomList:java.util.ArrayList<Int> = java.util.ArrayList()
        for (i in 1..101){
            randomList.add(i)
        }
        if (personList.size <= 1) {
            println("Перед началом игры необходимо добавить хотя бы двух игроков")
        }// вывести сообщение "Перед началом игры необходимо добавить хотя бы двух игроков" и завершить работу, если количество добавленных игроков меньше 2

        while (personList.size == 0) {
            val iteratorPerson=personList.iterator()
            val setNumber = Random.nextInt()
            randomList.remove(setNumber)
            while(iteratorPerson.hasNext()){
                val pers = iteratorPerson.next()
                for(i in pers.card.numbers){
                    if(i.value.contains(setNumber)){
                        i.value.remove(setNumber)
                        if(pers.card.numbers.size == 0) {
                            println("Победитель: ${pers.name}")
                        }
                    }
                }
            }


            // достать номер. Номер может быть в диапазоне от 1 до 99 включительно
            // после каждого выброшенного числа удалять это число из карточек всех игроков, если такое число имеется
            // выбрасывать новые числа до тех пор, пока не определится победитель
            // побеждает тот, у кого в одном из рядов нет больше чисел. Победителей может быть более одного
            // после того как появляется победитель, для каждого победителя вывести текст "Победитель: [имя_победителя]!!!"
        }
    }
}

class Card(val numbers: Map<Int, MutableSet<Int>>)

class Person(val name: String) {

    val card: Card = createCard()

    private fun createCard(): Card {
        val numbers: Set<Int> = generateNumbers()

        val iterator: Iterator<Int> = numbers.iterator()
        var currentLine = 1

        val cardNumbers: MutableMap<Int, MutableSet<Int>> = mutableMapOf(
            1 to mutableSetOf(),
            2 to mutableSetOf(),
            3 to mutableSetOf()
        )

        while (iterator.hasNext()) {
            val number = iterator.next()
            cardNumbers[currentLine]?.add(number)

            if (currentLine < 3) {
                currentLine++
            } else {
                currentLine = 1
            }
        }

        return Card(cardNumbers)
    }

    private fun generateNumbers(): Set<Int> {
        val numbers: MutableSet<Int> = mutableSetOf()

        while (numbers.size < NUMBERS_COUNT) {
            numbers.add(Random.nextInt(MIN_NUMBER, MAX_NUMBER))
        }

        return numbers
    }

    private companion object {

        private const val NUMBERS_COUNT = 15
        private const val MAX_NUMBER = 100
        private const val MIN_NUMBER = 1
    }
}


fun main(){

}