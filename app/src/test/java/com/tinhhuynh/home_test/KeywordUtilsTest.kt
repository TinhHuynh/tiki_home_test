package com.tinhhuynh.home_test

import com.tinhhuynh.home_test.utils.KeywordUtils
import junit.framework.Assert.assertEquals
import org.junit.Test

class KeywordUtilsTest {
    @Test
    fun testInsertNewlineInMiddle() {
        val keywords = arrayOf("bts", "son", "tai nghe bluetooth", "sac du pnong", "banh trung thu",
                "bitis hunter x", "anh chinh la thanh xuan cua em")

        val expecteds = arrayOf("bts", "son", "tai nghe\nbluetooth", "sac du\npnong",
                "banh\ntrung thu", "bitis\nhunter x", "anh chinh la\nthanh xuan cua em")

        keywords.forEachIndexed { index, keyword ->
            run {
                val expected = expecteds[index]
                val actual = KeywordUtils.insertNewlineInMiddle(keyword)
                assertEquals("Insert new line in middle of a string failed", expected, actual)
            }
        }
    }

}