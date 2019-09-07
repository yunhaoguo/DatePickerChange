package com.example.eric.datepicktest

import android.content.Context
import android.util.AttributeSet

/**
 * Author: yunhaoguo
 * Date: 2019-09-06
 */

class BirthdaySlotsView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : Slots<String>(context, attrs, defStyleAttr) {

    /**
     * 联动更新selected day
     */
    fun updateDayData(days: Int) {
        val newDayList = List(days) { index ->
            (index + 1).toString()
        }
        val selectedIndex = _wheelViewGroups[BirthdayChooseDialogFragment.DAY_SELECTED].selectedItem
        _wheelViewGroups[BirthdayChooseDialogFragment.DAY_SELECTED].setItems(newDayList)
        if (selectedIndex >= newDayList.size) {
            _wheelViewGroups[BirthdayChooseDialogFragment.DAY_SELECTED].setCurrentPosition(newDayList.size - 1)
            onLoopViewSelectedListener?.onLoopViewSelected(BirthdayChooseDialogFragment.DAY_SELECTED, newDayList.size.toString())
        } else {
            _wheelViewGroups[BirthdayChooseDialogFragment.DAY_SELECTED]
                .setCurrentPosition(selectedIndex)
            onLoopViewSelectedListener?.onLoopViewSelected(BirthdayChooseDialogFragment.DAY_SELECTED, (selectedIndex + 1).toString())
        }
    }

    /**
     * 联动更新selected month
     */
    fun updateMonthData(months: Int, monthList: List<String>) {
        val newMonthList = List(months) { index ->
            monthList[index]
        }
        val selectedIndex = _wheelViewGroups[BirthdayChooseDialogFragment.MONTH_SELECTED].selectedItem
        _wheelViewGroups[BirthdayChooseDialogFragment.MONTH_SELECTED].setItems(newMonthList)
        if (selectedIndex >= newMonthList.size) {
            _wheelViewGroups[BirthdayChooseDialogFragment.MONTH_SELECTED].setCurrentPosition(newMonthList.size - 1)
            onLoopViewSelectedListener?.onLoopViewSelected(BirthdayChooseDialogFragment.MONTH_SELECTED, newMonthList.size.toString())
        } else {
            _wheelViewGroups[BirthdayChooseDialogFragment.MONTH_SELECTED]
                .setCurrentPosition(selectedIndex)
            onLoopViewSelectedListener?.onLoopViewSelected(BirthdayChooseDialogFragment.MONTH_SELECTED, (selectedIndex + 1).toString())
        }
    }

    fun initPosition(dateArr: List<String>) {
        dateArr.forEachIndexed { pos, _ ->
            when (pos) {
                0 -> {
                    _wheelViewGroups[BirthdayChooseDialogFragment.YEAR_SELECTED].setCurrentPosition(dateArr[0].toInt() - BirthdayChooseDialogFragment.START_YEAR)
                }
                1 -> {
                    _wheelViewGroups[BirthdayChooseDialogFragment.MONTH_SELECTED].setCurrentPosition(dateArr[1].toInt() - 1)
                }
                2 -> {
                    _wheelViewGroups[BirthdayChooseDialogFragment.DAY_SELECTED].setCurrentPosition(dateArr[2].toInt() - 1)
                }
            }
        }

    }

    fun initDataList(pos: Int, count: Int, index: Int) {
        when (pos) {
            BirthdayChooseDialogFragment.YEAR_SELECTED -> {

            }
            BirthdayChooseDialogFragment.MONTH_SELECTED -> {
                val newMonthList = List(count) { i ->
                    BirthdayChooseDialogFragment.MONTH_MAP.keys.toList()[i]
                }
                _wheelViewGroups[pos].setItems(newMonthList)
                _wheelViewGroups[pos].setCurrentPosition(index)
            }
            BirthdayChooseDialogFragment.DAY_SELECTED -> {
                val newDayList = List(count) { i ->
                    (i + 1).toString()
                }
                _wheelViewGroups[pos].setItems(newDayList)
                _wheelViewGroups[pos].setCurrentPosition(index)
            }
        }
    }

    fun cancel() {
        _wheelViewGroups.forEach {
            it.cancelFuture()
        }
    }
}