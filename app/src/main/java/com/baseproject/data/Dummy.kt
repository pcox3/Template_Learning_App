package com.baseproject.data

import com.baseproject.ui.screens.Module
import com.baseproject.ui.screens.PathState

val modules = listOf(
    Module(0,"Programming Basics", PathState.COMPLETED, 1f),
    Module(1,"Git & Version Control", PathState.COMPLETED, 1f),
    Module(2,"Core Mobile UI Build", PathState.CURRENT, 0.30f),
    Module(3,"Learn React Component lifecycle", PathState.LOCKED, 0f),
    Module(4,"Access Device Features", PathState.LOCKED, 0f),
    Module(5,"Navigations and Forms", PathState.LOCKED, 0f),
    Module(6,"Node,js & Express", PathState.LOCKED, 0f),
    Module(7,"Backend Architecture", PathState.LOCKED, 0f),
    Module(8,"Authentication & Authorization", PathState.LOCKED, 0f),
    Module(9,"Write and Run Tests", PathState.LOCKED, 0f),
    Module(10,"Publish your Mobile App", PathState.LOCKED, 0f)

)

fun nextModule(): Module? {
    val index = modules.indexOfFirst { it.state == PathState.CURRENT }
            .takeIf { it != -1 }
            ?: modules.indexOfFirst { it.state == PathState.LOCKED }
                .takeIf { it != -1 }
            ?: modules.indexOfLast { it.state == PathState.COMPLETED }

    return modules.getOrNull(index)
}
