package com.tokopedia.testproject

import com.tokopedia.testproject.problems.algorithm.continousarea.Solution.maxContinuousArea
import org.junit.Test

import org.junit.Assert.*

class ContinuousAreaTest {

    @Test
    fun max_continous_area() {
        var value = maxContinuousArea(arrayOf(
                intArrayOf(0, 0),
                intArrayOf(0, 0)))
        assertEquals(4, value)

        value = maxContinuousArea(arrayOf(
                intArrayOf(1, 1, 1),
                intArrayOf(1, 1, 1),
                intArrayOf(1, 1, 1)))
        assertEquals(9, value)

        value = maxContinuousArea(arrayOf(
                intArrayOf(1, 0, 1, 0),
                intArrayOf(0, 0, 1, 1),
                intArrayOf(1, 1, 1, 0),
                intArrayOf(0, 1, 1, 0)))
        assertEquals(7, value)

        value = maxContinuousArea(arrayOf(
                intArrayOf(3, 1, 3, -1),
                intArrayOf(3, 1, 3, 3),
                intArrayOf(3, 3, 3, 2),
                intArrayOf(3, 2, 2, 2)))
        assertEquals(9, value)

        value = maxContinuousArea(arrayOf(
                intArrayOf(1, 2, 1, 2, 1),
                intArrayOf(1, 2, 1, 1, 1),
                intArrayOf(1, 1, 2, 1, 1),
                intArrayOf(1, 2, 2, 2, 1)))
        assertEquals(8, value)

        value = maxContinuousArea(arrayOf(
                intArrayOf(1, 2, 3, 3, 3),
                intArrayOf(1, 0, 3, 1, 3),
                intArrayOf(1, 1, 1, 1, 3),
                intArrayOf(1, 1, 1, 1, 3),
                intArrayOf(3, 3, 3, 3, 3)))
        assertEquals(12, value)

        value = maxContinuousArea(arrayOf(
                intArrayOf(0, 1, 2, 3, 4),
                intArrayOf(5, 6, 7, 8, 9),
                intArrayOf(10, 11, 12, 13, 14),
                intArrayOf(15, 16, 17, 18, 19),
                intArrayOf(20, 21, 22, 23, 24)))
        assertEquals(1, value)

        value = maxContinuousArea(arrayOf(
                intArrayOf(1, 0, 0, 1, 0),
                intArrayOf(0, 0, 1, 1, 1),
                intArrayOf(1, 0, 0, 0, 1),
                intArrayOf(0, 0, 1, 0, 1),
                intArrayOf(1, 0, 1, 1, 1)))
        assertEquals(11, value)
    }
}
