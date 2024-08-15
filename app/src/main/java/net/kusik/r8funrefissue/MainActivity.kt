package net.kusik.r8funrefissue

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import net.kusik.r8funrefissue.ui.theme.R8FunRefIssueTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val lazyPaging = LazyListPaging()

        setContent {
            R8FunRefIssueTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Text(
                        text = "${lazyPaging.snapshot}",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

class LazyListPaging {
    val snapshot = Snapshot<Unit>(emptyList(), ::requestLoad)

    // This function is not resolved even though it is used in the line above
    private fun requestLoad(index: Int?) {
        println("Requesting load")
    }
}

data class Snapshot<S>(
    private val backingList: List<S>,
    private val onLoad: (Int?) -> Unit,
) : List<S> by backingList {
}