package com.tinhhuynh.home_test.utils

import android.text.TextUtils
import kotlin.math.min

class KeywordHelper {

    private var distanceToPreviousBlankChar = 0
    private var distanceToNextBlankChar = 0
    private var previousBlankCharIndex = -1
    private var nextBlankCharIndex = -1
    private var foundPreviousCharIndex = false
    private var foundNextCharIndex = false
    private lateinit var keyword: String

    init {
        reset()
    }

    fun breakNewLine(kw: String): String {
        reset()
        this.keyword = kw.trim()
        val words = keyword.split(' ')
        // no need to process string which is empty or contains one word
        return if (TextUtils.isEmpty(keyword) || words.size == 1) {
            keyword
        } else {
            val replacementIndex =
                    if (words.size == 2) {
                        keyword.indexOf(' ')
                    } else {
                        findLineBreakIndex()
                    }
            var result = ""
            val sb = StringBuilder(keyword)
            if (replacementIndex != -1) {
                sb.setCharAt(replacementIndex, '\n')
            }
            result = sb.toString()
            result
        }
    }

    private fun findLineBreakIndex(): Int {
        val length = keyword.length
        println("$keyword length: $length")
        val midPosition = length / 2
        if (length % 2 == 0) {
            for (i in midPosition until length) {
                checkNextBlankCharAtIndex(i)
                val indexForPrevious = length - 1 - i
                checkPreviousBlankCharAtIndex(indexForPrevious)
                if (foundPreviousCharIndex && foundNextCharIndex) {
                    break
                }
            }
        } else {
            for (i in midPosition until length) {
                checkNextBlankCharAtIndex(i)
                val indexForPrevious = if (i == midPosition) midPosition else 2 * i - length
                checkPreviousBlankCharAtIndex(indexForPrevious)
                if (foundPreviousCharIndex && foundNextCharIndex) {
                    break
                }
            }
        }
        println("$keyword prev distance: $distanceToPreviousBlankChar / next distance: $distanceToNextBlankChar")
        println("$keyword prev index: $previousBlankCharIndex / next index: $nextBlankCharIndex")
        return if (min(distanceToPreviousBlankChar, distanceToNextBlankChar) ==
                distanceToPreviousBlankChar) previousBlankCharIndex else nextBlankCharIndex
    }

    private fun checkPreviousBlankCharAtIndex(index: Int) {
        if (!foundPreviousCharIndex) {
            distanceToPreviousBlankChar++
            if (keyword[index] == ' ') {
                previousBlankCharIndex = index
                foundPreviousCharIndex = true
            }
        }
    }

    private fun checkNextBlankCharAtIndex(index: Int) {
        if (!foundNextCharIndex) {
            distanceToNextBlankChar++
            if (keyword[index] == ' ') {
                nextBlankCharIndex = index
                foundNextCharIndex = true
            }
        }
    }

    private fun reset() {
        distanceToPreviousBlankChar = 0
        distanceToNextBlankChar = 0
        previousBlankCharIndex = -1
        nextBlankCharIndex = -1
        foundPreviousCharIndex = false
        foundNextCharIndex = false
        keyword = ""
    }
}