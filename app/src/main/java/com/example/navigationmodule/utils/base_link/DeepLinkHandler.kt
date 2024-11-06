package com.example.navigationmodule.utils.base_link

import android.content.Context
import android.net.Uri
import androidx.navigation.NavController
import com.example.navigationmodule.presentation.navigation.Screens

class DeepLinkHandler(val context: Context) {
    fun navigate(
        deepLinkMapDomain: DeepLinkMapDomain,
        navController: NavController,
        callback: (Boolean) -> Unit
    ) {
        val argList = createArgsFromUrl(deepLinkMapDomain)
        val page = deepLinkMapDomain.deepLinkMap["page"]?.split("?")

        if (page?.get(0) != null) {
            val id = context.resources.getIdentifier(
                page[0].removeSuffix("/"),
                "id",
                context.packageName
            )

            val resId = if (id == 0) {
                Screens.HomeScreen
            } else {
                Screens.ProductDetailsScreen
            }
            navController.navigate(resId)
        }

        if (deepLinkMapDomain.deepLinkMap["is_rating"] == "true") {
            callback(true)
        } else {
            callback(false)
        }

    }
}

fun createArgsFromUrl(deepLinkMapDomain: DeepLinkMapDomain): List<Pair<String?, String?>> {
    val argsList = mutableListOf<Pair<String?, String?>>()
    deepLinkMapDomain.deepLinkMap.filter { it.key != "page" }.forEach {
        argsList.add(it.key to it.value)
    }
    return argsList
}

data class DeepLinkMapDomain(
    val deepLinkMap: HashMap<String?, String?>,
)

fun Uri.toDeepLinkMapDomain(): DeepLinkMapDomain {
    val dataMap: HashMap<String?, String?> = hashMapOf()
    val args: Set<String> = this.queryParameterNames
    args.forEach {
        dataMap[it] = this.getQueryParameter(it)
    }
    return DeepLinkMapDomain(dataMap)

}