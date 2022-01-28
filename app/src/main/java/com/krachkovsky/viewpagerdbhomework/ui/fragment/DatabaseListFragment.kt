package com.krachkovsky.viewpagerdbhomework.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.krachkovsky.viewpagerdbhomework.databinding.FragmentDatabaseListBinding
import com.krachkovsky.viewpagerdbhomework.db.model.fromUserList
import com.krachkovsky.viewpagerdbhomework.db.room.UsersDatabase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class DatabaseListFragment : Fragment() {

    private var _binding: FragmentDatabaseListBinding? = null
    private val binding
        get() = requireNotNull(_binding) {
            "View was destroyed"
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentDatabaseListBinding.inflate(inflater, container, false)
            .also { binding ->
                _binding = binding
            }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {

            UsersDatabase.getInstance(requireContext()).usersDao().getAll()
                .flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
                .onEach {
                    tvUserList.text = fromUserList(it)
                }
                .launchIn(viewLifecycleOwner.lifecycleScope)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}