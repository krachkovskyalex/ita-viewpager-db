package com.krachkovsky.viewpagerdbhomework.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.krachkovsky.viewpagerdbhomework.databinding.FragmentAddToDatabaseBinding
import com.krachkovsky.viewpagerdbhomework.db.model.User
import com.krachkovsky.viewpagerdbhomework.db.room.UsersDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddToDatabaseFragment : Fragment() {

    private var _binding: FragmentAddToDatabaseBinding? = null
    private val binding
        get() = requireNotNull(_binding) {
            "View was destroyed"
        }

    private val db = UsersDatabase.getInstance(requireContext())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentAddToDatabaseBinding.inflate(inflater, container, false)
            .also { binding ->
                _binding = binding
            }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {

            btnAdd.setOnClickListener {

                val firstName = etFirstName.text.toString()
                val lastName = etLastName.text.toString()

                viewLifecycleOwner.lifecycleScope.launch {
                    withContext(Dispatchers.IO) {
                        db.usersDao()
                            .insert(User(firstName = firstName, lastName = lastName))
                    }
                    Toast.makeText(requireContext(), "User added!", Toast.LENGTH_SHORT).show()
                }
            }

            btnClear.setOnClickListener {
                etFirstName.text = null
                etLastName.text = null
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}