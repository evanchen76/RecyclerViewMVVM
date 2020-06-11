package evan.chen.tutorial.recyclerviewmvvm

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import evan.chen.tutorial.recyclerviewmvvm.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: MainAdapter
    private lateinit var viewModel: MainViewModel
    private lateinit var viewDataBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewDataBinding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        viewModel =
            ViewModelProvider(this, ViewModelFactory(Repository())).get(MainViewModel::class.java)

        viewDataBinding.viewModel = viewModel
        viewDataBinding.lifecycleOwner = this

        adapter = MainAdapter(viewModel)
        viewDataBinding.recyclerView.adapter = adapter

        viewModel.listLiveData.observe(this,
            Observer<List<Item>> {
                adapter.notifyDataSetChanged()
            })

        viewModel.openItemEvent.observe(this, Observer { event ->
            event.getContentIfNotHandled()?.let {
                val itemName = it
                //在這裡startActivity
            }
        })

        setupSnackBar()

    }

    private fun setupSnackBar() {
        viewModel.snackBarText.observe(this, Observer { event ->
            event.getContentIfNotHandled()?.let {
                val snackBarText = it
                Snackbar.make(layoutView, snackBarText, Snackbar.LENGTH_LONG).show()
            }
        })
    }

}