package com.example.adaptertp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StudentAdapter(
    private val context: Context,
    private var data: List<Student>
) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>(), Filterable {

    var dataFilterList = ArrayList<Student>()

    init {
        dataFilterList.addAll(data)
    }

    inner class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivGender: ImageView = itemView.findViewById(R.id.imageStudent)
        val tvStudentInfo: TextView = itemView.findViewById(R.id.textStudentDetails)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.student_item, parent, false)
        return StudentViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return dataFilterList.size
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = dataFilterList[position]

        val genderImage = if (student.gender.equals("male", ignoreCase = true)) {
            R.drawable.yellow_boy
        } else {
            R.drawable.blue_girl
        }
        holder.ivGender.setImageResource(genderImage)

        holder.tvStudentInfo.text = "${student.firstName} ${student.lastName}"
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString().toLowerCase().trim()

                if (charSearch.isEmpty()) {
                    dataFilterList = ArrayList(data)
                } else {
                    val resultList = ArrayList<Student>()
                    for (student in data) {
                        if (student.subject.name.equals(charSearch, ignoreCase = true) ||
                            student.firstName.toLowerCase().contains(charSearch) ||
                            student.lastName.toLowerCase().contains(charSearch)
                        ) {
                            resultList.add(student)
                        }
                    }
                    dataFilterList = resultList
                }

                val filterResults = FilterResults()
                filterResults.values = dataFilterList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                if (results?.values is ArrayList<*>) {
                    dataFilterList = results.values as ArrayList<Student>
                    notifyDataSetChanged()
                }
            }
        }
    }
}