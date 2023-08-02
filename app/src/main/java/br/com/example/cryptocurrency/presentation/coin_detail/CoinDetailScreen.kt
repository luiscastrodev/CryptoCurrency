package br.com.example.cryptocurrency.presentation.coin_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import br.com.example.cryptocurrency.presentation.coin_detail.components.CoinTag
import br.com.example.cryptocurrency.presentation.coin_detail.components.TeamListItem
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode

@Composable
fun CoinListScreen(
    viewModel: CoinDetailViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Box(
        modifier = Modifier.fillMaxSize(),
        //contentPadding = PaddingValues(20.dp)
    ) {
        state.coin?.let { coinDetail ->
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "${coinDetail.rank} ${coinDetail.name} (${coinDetail.symbol})",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.weight(8f)
                        )
                        Text(
                            text = if (coinDetail.isActive) "Active" else "inactive",
                            color = if (coinDetail.isActive) Color.Green else Color.Red,
                            fontStyle = FontStyle.Italic,
                            textAlign = TextAlign.End,
                            modifier = Modifier
                                .align(CenterVertically)
                                .weight(2f)
                        )
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = coinDetail.description,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = "Tags",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.height(15.dp))

                    FlowRow(
                        //mainAxisAlignment = 10.dp,
                        //crossAxisAlignment = 10.dp,
                        modifier = Modifier.fillMaxWidth(),
                        crossAxisSpacing = 0.dp,
                        mainAxisSpacing = 0.dp,
                        mainAxisSize = SizeMode.Expand,
                        lastLineMainAxisAlignment = FlowMainAxisAlignment.SpaceBetween
                    ) {
                        coinDetail.tags.forEach { tag ->
                            CoinTag(tag = tag)
                        }

                        Spacer(modifier = Modifier.height(15.dp))

                        Text(
                            text = "Team Members",
                            style = MaterialTheme.typography.bodyMedium
                        )

                        Spacer(modifier = Modifier.height(15.dp))
                    }
                }
                items(coinDetail.team) { teamMember ->
                    TeamListItem(
                        teamMember = teamMember,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    )
                    Divider()
                }
            }
        }

        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }

        if (state.isLoading) {
            CircularProgressIndicator(
                modifier =
                Modifier.align(Alignment.Center)
            )
        }
    }
}