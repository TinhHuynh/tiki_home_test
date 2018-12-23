package com.tinhhuynh.home_test.utils

import android.text.TextUtils

class KeywordUtils {
    companion object {

        fun insertNewlineInMiddle(keyword: String): String {
            // no need to process string which is empty or contains one word
            if (TextUtils.isEmpty(keyword) || keyword.indexOf(" ") == -1) {
                return keyword
            } else {
                val length = keyword.length
                val midLength = length / 2
                val midChar = keyword[midLength - 1]
                var result = ""
                val replacementIndex =
                        if (midChar == ' ')
                            midLength - 1
                        else {
                            findNearestEmptySpaceIndex(keyword, midLength - 1)
                        }
                val sb = StringBuilder(keyword)
                sb.setCharAt(replacementIndex, '\n')
                result = sb.toString()
                return result
            }
        }

        private fun findNearestEmptySpaceIndex(str: String, pivotIndex: Int): Int {
            var distance = Int.MAX_VALUE
            var reverseDistance = Int.MAX_VALUE
            var index = -1
            var reverseIndex = -1
            // iterate back from pivot char to first one
            for (i in pivotIndex downTo 0) {
                reverseDistance++
                if (str[i] == ' ') {
                    reverseIndex = i
                    break
                }
            }

            // iterate from pivot char to last one
            for (i in pivotIndex..(str.length - 1)) {
                distance++
                if (str[i] == ' ') {
                    index = i
                    break
                }
            }
            return if (Math.min(distance, reverseDistance) == distance) index else reverseIndex
        }
    }


}