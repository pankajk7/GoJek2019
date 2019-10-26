package com.pankaj.gojek.view

import android.content.Context
import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.Group
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import androidx.recyclerview.widget.RecyclerView
import com.pankaj.gojek.R
import com.pankaj.gojek.model.GithubRepoResponse
import com.pankaj.gojek.util.ExpandCardAnimation
import com.pankaj.gojek.util.gone
import com.pankaj.gojek.util.visible
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_repo_item_layout.view.*

class GithubRepoRVAdapter(
    private val context: Context,
    private var repoList: MutableList<GithubRepoResponse>
) : RecyclerView.Adapter<GithubRepoRVAdapter.RepoViewHolder>() {

    private var expandedViewPosition = -1

    override fun getItemCount(): Int = repoList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val view = LayoutInflater.from(context).inflate(
            R.layout.row_repo_item_layout,
            parent, false
        )
        return RepoViewHolder(view)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val obj = repoList[position]
        holder.tvName.text = obj.author
        holder.tvRepo.text = obj.name
        holder.tvDescription.text = obj.url
        holder.tvLanguage.text = obj.laguage ?: context.getString(R.string.not_available)
        holder.tvStar.text = obj.currentPeriodStars
        holder.tvFork.text = obj.forks
        Picasso.get().load(obj.avatar).into(holder.ivAvtar, object: Callback{
            override fun onSuccess() {
                val imageBitmap = (holder.ivAvtar.drawable as BitmapDrawable).bitmap
                val imageDrawable = RoundedBitmapDrawableFactory.create(
                    context.resources, imageBitmap)
                imageDrawable.isCircular = true
                imageDrawable.cornerRadius = Math.max(imageBitmap.width, imageBitmap.height) / 2.0f
                holder.ivAvtar.setImageDrawable(imageDrawable)
            }

            override fun onError(e: Exception?) {
                val imageBitmap = (holder.ivAvtar.drawable as BitmapDrawable).bitmap
                val imageDrawable = RoundedBitmapDrawableFactory.create(
                    context.resources, imageBitmap)
                imageDrawable.isCircular = true
                imageDrawable.cornerRadius = Math.max(imageBitmap.width, imageBitmap.height) / 2.0f
                holder.ivAvtar.setImageDrawable(
                    ContextCompat.getDrawable(context, R.drawable.ic_launcher_background))
            }
        })
        setColorFilter(holder.viewLanguage.background, Color.parseColor(obj.laguageColor ?: "#FFFFFF"))
        if (position != expandedViewPosition) {
            holder.group.gone()
            holder.viewDivider.visible()
        } else {
            holder.group.visible()
            holder.viewDivider.gone()
        }
        holder.itemView.tag = position
        holder.itemView.setOnClickListener(onClickListener)
    }

    private val onClickListener = View.OnClickListener {
        val position = it.tag as Int
        val a = ExpandCardAnimation(it.group, 500, ExpandCardAnimation.EXPAND)
        expandedViewPosition = position
        notifyDataSetChanged()
    }

    private fun setColorFilter(drawable: Drawable, color: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            drawable.colorFilter = BlendModeColorFilter(color, BlendMode.SRC_ATOP)
        } else {
            drawable.setColorFilter(color, PorterDuff.Mode.SRC_ATOP)
        }
    }

    class RepoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var viewHeight = 0
        val ctlItem: ConstraintLayout = itemView.findViewById(R.id.ctl_item)
        val group: Group = itemView.findViewById(R.id.group)
        val tvName: TextView = itemView.findViewById(R.id.tv_name)
        val tvRepo: TextView = itemView.findViewById(R.id.tv_repo_name)
        val tvDescription: TextView = itemView.findViewById(R.id.tv_description)
        val tvLanguage: TextView = itemView.findViewById(R.id.tv_language)
        val tvStar: TextView = itemView.findViewById(R.id.tv_star)
        val tvFork: TextView = itemView.findViewById(R.id.tv_fork)
        val ivAvtar: ImageView = itemView.findViewById(R.id.iv_avtar)
        val viewLanguage: View = itemView.findViewById(R.id.iv_language)
        val ivStar: ImageView = itemView.findViewById(R.id.iv_star)
        val ivFork: ImageView = itemView.findViewById(R.id.iv_fork)
        val viewShadow: View = itemView.findViewById(R.id.view_shadow)
        val viewDivider: View = itemView.findViewById(R.id.view_divider)

//        init {
//            ctlItem.viewTreeObserver
//                .addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
//                    override fun onGlobalLayout() {
//                        ctlItem.viewTreeObserver.removeOnGlobalLayoutListener(this)
//                        viewHeight = ctlItem.height
//                    }
//                })
//            ctlItem.setOnClickListener {
//                val position = it.tag as Int
//                val a = ExpandCardAnimation(group, 500, ExpandCardAnimation.EXPAND)
//                a.height = viewHeight
//                group.startAnimation(a)
//            }
//        }
    }
}