package com.rtaylor.androidfeatures

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rtaylor.bubbles.BubblesActivity
import com.rtaylor.motioneditor.MotionEditorActivity
import kotlinx.android.synthetic.main.item_feature.view.*

class FeatureListAdapter : RecyclerView.Adapter<FeatureListViewHolder>() {

    private val features = listOf(
        MotionEditorFeatureLauncher(),
        BubblesFeatureLauncher()
    )

    override fun getItemCount() = features.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeatureListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_feature, parent, false)

        return FeatureListViewHolder(view)
    }

    override fun onBindViewHolder(holder: FeatureListViewHolder, position: Int) {
        holder.bind(features[position])
    }
}

class FeatureListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(featureLauncher: FeatureLauncher) {
        itemView.featureTitle.text = featureLauncher.title

        itemView.setOnClickListener {
            featureLauncher.launchFeature()
        }
    }
}

interface FeatureLauncher {
    val title: String

    fun launchFeature()
}

class MotionEditorFeatureLauncher : FeatureLauncher {
    override val title: String = "Motion Editor"

    override fun launchFeature() {
        App.instance.run {
            startActivity(
                Intent(this, MotionEditorActivity::class.java).apply {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }
            )
        }
    }
}

class BubblesFeatureLauncher : FeatureLauncher {
    override val title: String = "Bubbles"

    override fun launchFeature() {
        App.instance.run {
            startActivity(
                Intent(this, BubblesActivity::class.java).apply {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }
            )
        }
    }
}
