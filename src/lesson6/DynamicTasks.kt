@file:Suppress("UNUSED_PARAMETER")

package lesson6

import java.io.File

/**
 * Наибольшая общая подпоследовательность.
 * Средняя
 *
 * Дано две строки, например "nematode knowledge" и "empty bottle".
 * Найти их самую длинную общую подпоследовательность -- в примере это "emt ole".
 * Подпоследовательность отличается от подстроки тем, что её символы не обязаны идти подряд
 * (но по-прежнему должны быть расположены в исходной строке в том же порядке).
 * Если общей подпоследовательности нет, вернуть пустую строку.
 * При сравнении подстрок, регистр символов *имеет* значение.
 */
fun longestCommonSubSequence(first: String, second: String): String {
    TODO()
}

/**
 * Наибольшая возрастающая подпоследовательность
 * Средняя
 *
 * Дан список целых чисел, например, [2 8 5 9 12 6].
 * Найти в нём самую длинную возрастающую подпоследовательность.
 * Элементы подпоследовательности не обязаны идти подряд,
 * но должны быть расположены в исходном списке в том же порядке.
 * Если самых длинных возрастающих подпоследовательностей несколько (как в примере),
 * то вернуть ту, в которой числа расположены раньше (приоритет имеют первые числа).
 * В примере ответами являются 2, 8, 9, 12 или 2, 5, 9, 12 -- выбираем первую из них.
 */
fun longestIncreasingSubSequence(list: List<Int>): List<Int> {
    TODO()
}

/**
 * Самый короткий маршрут на прямоугольном поле.
 * Сложная
 *
 * В файле с именем inputName задано прямоугольное поле:
 *
 * 0 2 3 2 4 1
 * 1 5 3 4 6 2
 * 2 6 2 5 1 3
 * 1 4 3 2 6 2
 * 4 2 3 1 5 0
 *
 * Можно совершать шаги длиной в одну клетку вправо, вниз или по диагонали вправо-вниз.
 * В каждой клетке записано некоторое натуральное число или нуль.
 * Необходимо попасть из верхней левой клетки в правую нижнюю.
 * Вес маршрута вычисляется как сумма чисел со всех посещенных клеток.
 * Необходимо найти маршрут с минимальным весом и вернуть этот минимальный вес.
 *
 * Здесь ответ 2 + 3 + 4 + 1 + 2 = 12
 */

//Трудоемкость - O(height * width)
//Ресурсоемкость - O(height * width)

fun shortestPathOnField(inputName: String): Int {
    val fieldStrings = ArrayList<List<String>>()
    File(inputName).forEachLine { fieldStrings.add(it.split(" ", "\n")) }
    val height = fieldStrings.size
    val width = fieldStrings[0].size
    val field = Array(height) { IntArray(width) }
    field[0][0] = fieldStrings[0][0].toInt()
    for (i in 1 until height) {
        field[i][0] = field[i - 1][0] + fieldStrings[i][0].toInt()
    }
    for (i in 1 until width) {
        field[0][i] = field[0][i - 1] + fieldStrings[0][i].toInt()
    }
    for (i in 1 until height) {
        for (j in 1 until width) {
            field[i][j] = minOfThree(field[i - 1][j], field[i][j - 1], field[i - 1][j - 1]) + fieldStrings[i][j].toInt()
        }
    }
    return field[height - 1][width - 1]
}

private fun minOfThree(first: Int, second: Int, third: Int): Int {
    var min = first + second + third
    if (first >= second && third >= second) {
        min = second
    }
    if (second >= first && third >= first) {
        min = first
    }
    if (first >= third && second >= third) {
        min = third
    }
    return min
}

// Задачу "Максимальное независимое множество вершин в графе без циклов"
// смотрите в уроке 5