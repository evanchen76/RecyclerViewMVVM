package evan.chen.tutorial.recyclerviewmvvm

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import evan.chen.tutorial.recyclerviewmvvm.databinding.ItemLayoutBinding

class MainAdapter(private val viewModel: MainViewModel) :
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    var list: List<Item>? = viewModel.listLiveData.value

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list!![position]
        holder.bind(viewModel, item)
    }

    override fun getItemCount(): Int {
        return list?.count() ?: 0
    }

    class ViewHolder private constructor(private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(viewModel: MainViewModel, item: Item) {

            binding.viewModel = viewModel
            binding.item = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemLayoutBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }

}
