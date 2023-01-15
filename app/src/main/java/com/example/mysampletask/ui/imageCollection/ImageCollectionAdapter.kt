package com.example.mysampletask.ui.imageCollection

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mysampletask.R
import com.example.mysampletask.data.models.GetDetails
import com.example.mysampletask.databinding.ImageListItemBinding
import com.example.mysampletask.ui.utils.displayImage
import com.squareup.picasso.Picasso

class ImageCollectionAdapter(
    private val context: Context,
    private var collectionList: ArrayList<GetDetails>
):
    RecyclerView.Adapter<ImageCollectionAdapter.ImageCollectionViewHolder>(){

    lateinit var imageListItemBinding: ImageListItemBinding

    inner class ImageCollectionViewHolder(private val listItemBinding: ImageListItemBinding) : RecyclerView.ViewHolder(listItemBinding.root) {
        fun bindData(dataListItem: GetDetails) = with(listItemBinding){
            dataListItem.let { details->
                itemListDetails = details
                imgThumbnailIv.displayImage(context,details.urls?.smallS3)
                userProfileThumbnailIv.displayImage(context,details.user?.profileImage?.medium)

                imgThumbnailIv.setOnClickListener{
                    selectedItemListener?.invoke(details)
                }
                userProfileThumbnailIv.setOnClickListener {
                    selectedProfileListener?.invoke(details)
                }
                userProfileNameTv.setOnClickListener {
                    selectedProfileListener?.invoke(details)
                }
            }
        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<GetDetails>() {
        override fun areItemsTheSame(oldItem: GetDetails, newItem: GetDetails): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: GetDetails, newItem: GetDetails): Boolean {
            return oldItem == newItem
        }
    }


    private val differ = AsyncListDiffer(this, differCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageCollectionAdapter.ImageCollectionViewHolder {
        imageListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),R.layout.image_list_item,parent,false)
        return ImageCollectionViewHolder(imageListItemBinding)
    }

    override fun onBindViewHolder(holder: ImageCollectionAdapter.ImageCollectionViewHolder, position: Int) {
        holder.bindData(collectionList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun updateItems(itemList: ArrayList<GetDetails>) {
        this.collectionList = itemList
        differ.submitList(itemList)
    }

    private var selectedItemListener : ((item: GetDetails) -> Unit)? = null
    fun selectedItemListenerOnClick(listener : (item: GetDetails) -> Unit){
        this.selectedItemListener = listener
    }

    private var selectedProfileListener : ((details: GetDetails) -> Unit)? = null
    fun selectedProfileListenerOnClick(listener : (details: GetDetails) -> Unit){
        this.selectedProfileListener = listener
    }
}