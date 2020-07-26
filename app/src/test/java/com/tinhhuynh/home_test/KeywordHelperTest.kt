package com.tinhhuynh.home_test

import com.tinhhuynh.home_test.utils.KeywordHelper
import junit.framework.Assert.assertEquals
import org.junit.Test

class KeywordHelperTest {
    @Test
    fun testBreakNewLine() {
        val keywords = arrayOf(
                "banh trung thu",
                "tui deo cheo",
                "sac du phong",
                "binh giu nhiet",
                "banh trung thu kinh do",
                "bts",
                "son",
                "xiaomi",
                "tai nghe",
                "tai nghe bluetooth",
                "sac du pnong",
                "banh trung thu",
                "bitis hunter x",
                "anh chinh la thanh xuan cua em")

        val expecteds = arrayOf(
                "banh\ntrung thu",
                "tui deo\ncheo",
                "sac du\nphong",
                "binh giu\nnhiet",
                "banh trung\nthu kinh do",
                "bts",
                "son",
                "xiaomi",
                "tai\nnghe",
                "tai nghe\nbluetooth",
                "sac du\npnong",
                "banh\ntrung thu",
                "bitis\nhunter x",
                "anh chinh la\nthanh xuan cua em")
        val keywordHelper = KeywordHelper()
        keywords.forEachIndexed { index, keyword ->
            run {
                val expected = expecteds[index]
                val actual = keywordHelper.breakNewLine(keyword)
                assertEquals("Test break new line failed: ", expected, actual)
            }
        }
    }
}