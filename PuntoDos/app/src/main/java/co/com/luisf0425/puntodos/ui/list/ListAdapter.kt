package co.com.luisf0425.puntodos.ui.list

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import co.com.luisf0425.puntodos.R
import co.com.luisf0425.puntodos.model.Post

class ListAdapter(private val context: Context, private val list: MutableList<Post>,
                  fragment: Fragment
): RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    private val listener: onItemClickListener

    init {
        this.listener = fragment as onItemClickListener
    }


    override fun getItemCount(): Int {
        return list.size
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {

        var post = list[position]

        holder!!.title!!.text = post.title
        holder.body!!.text = post.body

        holder.layout!!.setOnClickListener {
            listener.itemDetail(post)
        }

        if(list[position].isRead){
            holder.layout.setBackgroundColor(context.getColor(R.color.colorWhite))
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false)
        if(viewType < 20){
            if(!list[viewType].isRead){
                itemView.setBackgroundColor(context.getColor(R.color.colorBluePost))
            }
        }
        return ListViewHolder(itemView)
    }

    fun removeAt(position: Int) {
        listener.itemRemoveClick(list[position])
        list.remove(list[position])
    }

    fun removeAll(){
        val size = list.size
        if(size > 0){
            list.clear()
        }

        notifyItemRangeRemoved(0,size)
    }

    class ListViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        var layout = itemView.findViewById<ConstraintLayout>(R.id.item_layout)
        val title = itemView.findViewById<TextView>(R.id.item_title)
        val body = itemView.findViewById<TextView>(R.id.item_body)
    }

    interface onItemClickListener {
        fun itemRemoveClick(post: Post) //listener for remove a item
        fun itemDetail(post : Post) //listener for open detail of a item
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}