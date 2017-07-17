package exmcollapsing.test.com.kotlinhelloworld

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import exmcollapsing.test.com.kotlinhelloworld.exception.ApiException
import exmcollapsing.test.com.kotlinhelloworld.subscriber.RestAPIObserver
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var list: MutableList<TestResponse.TypePhone> = mutableListOf()
    var adapter = TestAdapter(this, list)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lv_content.adapter = adapter
        tv_load.setOnClickListener {
            TestApi().get(object :RestAPIObserver<TestResponse>(this, false) {
                override fun onSuccess(t: TestResponse) {
                    list.addAll(t.user_guides)
                    adapter.notifyDataSetChanged()
                }

                override fun onApiError(e: ApiException) {
                    Log.i("TAG", "TAG")
                }
            })
        }
        lv_content.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(this,list[position].detail,Toast.LENGTH_LONG).show()
        }
    }
    fun TestAdapter.Toast(){

    }
}
