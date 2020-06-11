package evan.chen.tutorial.recyclerviewmvvm

interface IRepository {
    fun getItems(itemCallback: ItemCallback)

    interface ItemCallback {

        fun onItemsResult(items: List<Item>)
    }
}

class Repository : IRepository {

    override fun getItems(itemCallback: IRepository.ItemCallback) {
        val list = mutableListOf<Item>()
        (1..20).forEach {
            list.add(Item("Text $it", false))
        }

        itemCallback.onItemsResult(list)
    }
}

