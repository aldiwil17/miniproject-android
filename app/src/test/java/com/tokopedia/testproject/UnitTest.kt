package com.tokopedia.testproject

import com.tokopedia.testproject.problems.algorithm.maxrectangle.Solution.maxRect
import org.junit.Test

import org.junit.Assert.*

class UnitTest {
    @Test
    fun max_rect() {
        var value = maxRect(arrayOf(
                intArrayOf(0, 0),
                intArrayOf(0, 0)))
        assertEquals(0, value)

        value = maxRect(arrayOf(
                intArrayOf(1, 1, 1),
                intArrayOf(1, 1, 1),
                intArrayOf(1, 1, 1)))
        assertEquals(9, value)

        value = maxRect(arrayOf(
                intArrayOf(1, 0, 1, 0),
                intArrayOf(0, 0, 1, 1),
                intArrayOf(1, 1, 1, 0),
                intArrayOf(0, 1, 1, 0)))
        assertEquals(4, value)

        value = maxRect(arrayOf(
                intArrayOf(1, 0, 1, 0),
                intArrayOf(1, 0, 1, 1),
                intArrayOf(1, 1, 1, 0),
                intArrayOf(1, 0, 0, 0)))
        assertEquals(4, value)

        value = maxRect(arrayOf(
                intArrayOf(1, 0, 1, 0, 1),
                intArrayOf(1, 0, 1, 1, 1),
                intArrayOf(1, 1, 1, 1, 1),
                intArrayOf(1, 0, 0, 0, 1)))
        assertEquals(6, value)

        value = maxRect(arrayOf(
                intArrayOf(1, 0, 1, 0, 1),
                intArrayOf(1, 0, 1, 1, 1),
                intArrayOf(1, 1, 1, 1, 1),
                intArrayOf(1, 1, 1, 1, 0),
                intArrayOf(1, 0, 0, 0, 1)))
        assertEquals(8, value)

        value = maxRect(arrayOf(
                intArrayOf(0, 0, 1, 0, 1),
                intArrayOf(0, 0, 1, 1, 1),
                intArrayOf(1, 1, 1, 1, 1),
                intArrayOf(0, 1, 1, 1, 1),
                intArrayOf(1, 0, 0, 0, 1)))
        assertEquals(9, value)

        value = maxRect(arrayOf(
                intArrayOf(0, 1, 0, 1, 0),
                intArrayOf(0, 1, 1, 1, 1),
                intArrayOf(1, 1, 0, 0, 1),
                intArrayOf(0, 1, 1, 1, 1),
                intArrayOf(1, 0, 1, 1, 1)))
        assertEquals(6, value)
    }

}
