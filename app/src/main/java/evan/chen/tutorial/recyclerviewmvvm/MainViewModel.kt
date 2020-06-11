package evan.chen.tutorial.recyclerviewmvvm

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class MainViewModel(private val repository: IRepository) : ViewModel() {
    var listLiveData: MutableLiveData<List<Item>> = MutableLiveData()

    val empty: LiveData<Boolean> = Transformations.map(listLiveData) {
        it.isEmpty()
    }

    var openItemEvent: MutableLiveData<Event<String>> = MutableLiveData()
    var snackBarText: MutableLiveData<Event<String>> = MutableLiveData()

    init {
        getData()
    }

    fun getData() {
        repository.getItems(object : IRepository.ItemCallback {
            override fun onItemsResult(items: List<Item>) {
                viewModelScope.launch {
                    listLiveData.value = items
                }
            }
        })
    }

    fun selectItem(item: Item, selected: Boolean) = viewModelScope.launch {
        item.isSelected = selected
        snackBarText.value = Event("${item.itemName}:$selected")
    }

    fun openItem(itemName: String) {
        openItemEvent.value = Event(itemName)
    }
}


