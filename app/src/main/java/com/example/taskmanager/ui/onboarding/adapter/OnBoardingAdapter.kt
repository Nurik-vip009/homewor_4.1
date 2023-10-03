package com.example.taskmanager.ui.onboarding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.taskmanager.databinding.ItemOnboardingBinding
import com.example.taskmanager.ui.task.model.OnBoarding

class OnBoardingAdapter : Adapter<OnBoardingAdapter.OnBoardingVieHolder>(){

    private val data = arrayListOf(
        OnBoarding("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRK2-tdqJmH1a3XboJvJir-Au66ch7nqmi3bg&usqp=CAU.png", "Elite House", "Здесь могла быть ваша реклама"),
        OnBoarding("https://re.kg/image/43edc6f6-16cf-4726-9c71-175c94efe1cf/500/500/tesla-center-bishkek-%D0%BF%D0%B5%D1%80%D0%B2%D0%B0%D1%8F-%D0%BA%D1%80%D1%83%D0%BF%D0%BD%D0%B0%D1%8F-%D0%BA%D0%BE%D0%BC%D0%BF%D0%B0%D0%BD%D0%B8%D1%8F-%D0%BF%D1%80%D0%B5%D0%B4%D0%BE%D1%81%D1%82%D0%B0%D0%B2%D0%BB%D1%8F%D1%8E%D1%89%D0%B0%D1%8F-%D1%8D%D0%BB%D0%B5%D0%BA%D1%82%D1%80%D0%BE%D0%BC%D0%BE%D0%B1%D0%B8%D0%BB%D0%B8-%D0%B2-%D0%BA%D1%8B%D1%80%D0%B3%D1%8B%D0%B7%D1%81%D1%82%D0%B0%D0%BD%D0%B5?set=True&path=000000000114366%2F43edc6f6-16cf-4726-9c71-175c94efe1cf.jpg", "Tesla", "Здесь могла быть ваша реклама"),
        OnBoarding("https://lingua-airlines.ru/wp-content/uploads/2017/01/annual-travel-insurance-plan-at-directasia.jpg", "Travel", "Здесь могла быть ваша реклама"),
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingVieHolder {
        return OnBoardingVieHolder(ItemOnboardingBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: OnBoardingVieHolder, position: Int) {
        holder.bind(data[position])
    }

    inner class OnBoardingVieHolder(private val binding: ItemOnboardingBinding)
        : ViewHolder(binding.root) {
            
            fun bind(onBoarding: OnBoarding){
                Glide.with(binding.ivBoard).load(onBoarding.image).into(binding.ivBoard)
                binding.tvTitle.text = onBoarding.title
                binding.tvDesc.text = onBoarding.desc
            }
    }
}
