package com.rahnumasharib.true_caller_assignment.ui.main.view

import android.os.Bundle
import android.text.Html
import android.view.*
import android.text.TextUtils
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.rajkumarrajan.mvvm_architecture.databinding.ActivityMainBinding
import com.rahnumasharib.true_caller_assignment.ui.main.adapter.MainAdapter
import com.rahnumasharib.true_caller_assignment.ui.main.adapter.wordCounterAdapter
import com.rahnumasharib.true_caller_assignment.ui.main.viewmodel.MainViewModel
import com.rahnumasharib.true_caller_assignment.utils.Status
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    //view model
    private val mainViewModel: MainViewModel by viewModels()

    //view binding
    private lateinit var viewBinding : ActivityMainBinding

    //array list that store all character of position 10,20,30,40.............
    private val tenthCharacterArrayList = ArrayList<Char>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //initializing the binding class
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        // we now set the contentview as the binding.root
        setContentView(viewBinding.root)

        setUiCall()
    }

    fun setUiCall(){
        //click event on fetch data button on main screeen
        viewBinding.fetchDataButton.setOnClickListener {
            viewBinding.tenthProgressBar.visibility = View.VISIBLE
            searchTenthCharacter()
        }
    }

    //for observe the data for fetch character who come in 10 position
    fun searchTenthCharacter(){
        mainViewModel.fetchTenthCharacter().observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { serverResponce ->
                        retriveTenthElement(htmlTagRemover(serverResponce.toString())) }
                }
                Status.LOADING -> {
                    //right your code till data status is loading
                }
                Status.ERROR -> {
                    //Handle Error here
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    serchEachTenthElement()
                }
            }
        })
    }

    //for observe the data for fetch character who come in each 10 position
    //like 10,20,30,40 .....................
    fun serchEachTenthElement(){
        viewBinding.eachTenthProgressBar.visibility = View.VISIBLE
        mainViewModel.fetchEachTenthCharacter().observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { serverResponce ->
                        retriveEachTenthElement(htmlTagRemover(serverResponce as String))
                    }
                }
                Status.LOADING -> {
                    //right your code till data status is loading
                }
                Status.ERROR -> {
                    //Handle Error
                    viewBinding.eachTenthProgressBar.visibility = View.INVISIBLE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    sameWordCounter()
                }
            }

        })

    }

    //for observe the data for fetch whole character from web url
    fun sameWordCounter(){
        viewBinding.sameWordCounterProgressBar.visibility = View.VISIBLE
        mainViewModel.wholeWordCounter().observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let {
                            serverResponce -> wholeWordCunter(htmlTagRemover(serverResponce as String)) }
                }
                Status.LOADING -> {
                    //right your code till data status is loading
                }
                Status.ERROR -> {
                    //Handle Error
                    viewBinding.sameWordCounterProgressBar.visibility = View.INVISIBLE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })

    }

    // used to get character on 10 positon in string
    private fun retriveTenthElement(wholeContent: String) {
        try {
            setTentElementInLayout(wholeContent.get(9).toString());
        }catch (e : Exception){
            //handle exception
        }
    }

    // used to get character on 10 positon in string
    private fun retriveEachTenthElement(wholeContent: String) {
        try {
            for(i in 9..1000 step 10){
                if(wholeContent.get(i).toString().isNotEmpty() && !wholeContent.get(i).toString().equals(" ")){
                    // add element only if that not blank or empty
                    tenthCharacterArrayList.add(wholeContent.get(i))
                }
            }
            setEachTentElementInLayout()
        }catch (e : Exception){
            //handle exception
            e.printStackTrace()
        }
    }

    //convert string into char arry by spilting string into
    //char array through space (" ")
    private fun wholeWordCunter(wholeContent: String) {
        try {
            val contentArray = wholeContent.split(" ")
            uniqueWordCounter(contentArray)
        }catch (e : Exception){
            //handle exception
            var a = e.message
            e.printStackTrace()
        }
    }

    // use recycle view to display element of 10 position
    private fun setTentElementInLayout(tenthCharacter: String) {
        viewBinding.tenthElementContainer.visibility = View.VISIBLE
        viewBinding.tenthProgressBar.visibility = View.INVISIBLE
        viewBinding.setTenthElement.text = tenthCharacter
        serchEachTenthElement()
    }

    // use recycle view to display element of each 10 position
    // 10,20,30,40 .............................
    private fun setEachTentElementInLayout() {
        val adapter = MainAdapter(tenthCharacterArrayList)
        viewBinding.eachTenthElementRecycleView.layoutManager = GridLayoutManager(this,1, GridLayoutManager.HORIZONTAL,false)
        viewBinding.eachTenthElementRecycleView.adapter = adapter
        viewBinding.eachTenthProgressBar.visibility = View.INVISIBLE
        sameWordCounter()
    }

    // server responce contain all the string which contain both
    // html and java script tag
    //this will remove all the html tag from string and retern
    // rest of the string
    fun htmlTagRemover(htmlTagString : String) : String{
        val spanned = Html.fromHtml(htmlTagString)
        val chars = CharArray(spanned.length)
        TextUtils.getChars(spanned, 0, spanned.length, chars, 0)
        return String(chars)
    }

    // this will help us to calculate how many time same words
    // presented into string and store them to a hashmap
    fun uniqueWordCounter(arrayWords : List<String>){
        val hashMap = HashMap<String,String>()
        for ( i in 0..arrayWords.size-1){
            if(hashMap.containsKey(arrayWords[i])){
                var updatedNumberOfThisWord = hashMap.get(arrayWords.get(i))?.toInt()
                hashMap.put(arrayWords.get(i), (updatedNumberOfThisWord?.plus(1)).toString())
            }else{
                hashMap.put(arrayWords.get(i), "1")
            }
        }
        passKeyToAdapterForUniqueWord(arrayWords,hashMap)
    }


    // use recycle view to display how many time each number
    // appear in the string
    fun passKeyToAdapterForUniqueWord(arrayWord : List<String>, hashMap: HashMap<String,String>){
        val adapter = wordCounterAdapter(arrayWord , hashMap)
        viewBinding.eachWordCounterRecycleView.layoutManager = StaggeredGridLayoutManager(2, GridLayoutManager.HORIZONTAL)
        viewBinding.eachWordCounterRecycleView.adapter = adapter
        viewBinding.sameWordCounterProgressBar.visibility = View.INVISIBLE
    }
}
