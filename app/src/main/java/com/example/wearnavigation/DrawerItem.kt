package com.example.wearnavigation

class DrawerItem(var name: String, var navigationIcon: String) {

    fun setName(name: String): String {
        return name.also { this.name = it }
    }

    fun setNavigation(navigationIcon: String): String {
        return navigationIcon.also { this.navigationIcon = it }
    }
}