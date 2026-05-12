package com.example.groceryx.ui.home

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.groceryx.R

class BannerAdapter(private val banners: List<String>) :
    RecyclerView.Adapter<BannerAdapter.BannerVH>() {

    private val colors = listOf("#1565C0", "#2E7D32", "#F57C00")

    inner class BannerVH(view: View) : RecyclerView.ViewHolder(view) {
        val tvBanner: TextView = view.findViewById(R.id.tvBanner)
        val root: View = view
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerVH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_banner, parent, false)
        return BannerVH(view)
    }

    override fun onBindViewHolder(holder: BannerVH, position: Int) {
        holder.tvBanner.text = banners[position]
        holder.root.setBackgroundColor(Color.parseColor(colors[position % colors.size]))
    }

    override fun getItemCount() = banners.size
}
