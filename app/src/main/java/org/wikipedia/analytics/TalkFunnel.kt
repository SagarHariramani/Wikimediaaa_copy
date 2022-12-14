package org.wikipedia.analytics

import org.json.JSONObject
import org.wikipedia.Constants
import org.wikipedia.WikipediaApp
import org.wikipedia.auth.AccountUtil
import org.wikipedia.page.PageTitle

class TalkFunnel constructor(private val title: PageTitle, private val invokeSource: Constants.InvokeSource) :
        TimedFunnel(WikipediaApp.instance, SCHEMA_NAME, REV_ID, SAMPLE_LOG_ALL) {

    override fun preprocessData(eventData: JSONObject): JSONObject {
        preprocessData(eventData, "source", invokeSource.value)
        preprocessData(eventData, "anon", !AccountUtil.isLoggedIn)
        preprocessData(eventData, "pageNS", title.namespace().code().toString())
        return super.preprocessData(eventData)
    }

    fun logOpenTalk() {
        log("action", "open_talk")
    }

    fun logOpenTopic() {
        log("action", "open_topic")
    }

    fun logNewTopicClick() {
        log("action", "new_topic_click")
    }

    fun logReplyClick() {
        log("action", "reply_click")
    }

    fun logRefresh() {
        log("action", "refresh")
    }

    fun logChangeLanguage() {
        log("action", "lang_change")
    }

    fun logEditSubmit() {
        log("action", "submit")
    }

    fun logOpenSort() {
        log("action", "open_sort")
    }

    fun logOpenArchive() {
        log("action", "open_archive")
    }

    fun logSortOrderPublished(ascendingOrder: Boolean) {
        val order = if (ascendingOrder) "ascending" else "descending"
        log("action", "sort_order_published_$order")
    }

    fun logSortOrderUpdated(ascendingOrder: Boolean) {
        val order = if (ascendingOrder) "ascending" else "descending"
        log("action", "sort_order_updated_$order")
    }

    fun logSortOrderTopic(ascendingOrder: Boolean) {
        val order = if (ascendingOrder) "ascending" else "descending"
        log("action", "sort_order_topic_$order")
    }

    fun logThreadGlobalCollapse() {
        log("action", "global_collapse")
    }

    fun logThreadGlobalExpand() {
        log("action", "global_expand")
    }

    fun logThreadItemCollapse() {
        log("action", "item_collapse")
    }

    fun logThreadItemExpand() {
        log("action", "item_expand")
    }

    companion object {
        private const val SCHEMA_NAME = "MobileWikiAppTalk"
        private const val REV_ID = 21020341
    }
}
