import SwiftUI
import shared

struct ContentView: View {
    @StateObject private var viewModel = StopwatchViewModelWrapper()
    
    var body: some View {
        VStack {
            Text(viewModel.uiState.formattedTime)
                .padding()
            
            HStack(spacing: 8) {
                Button(action: viewModel.onStartClicked, label: {
                    Text("Start")
                })
                Button(action: viewModel.onStopClicked, label: {
                    Text("Stop")
                })
            }
            Button(action: viewModel.onCopyClicked, label: {
                Image(systemName: "doc.on.doc")
            })
            .foregroundStyle(Color(red: 0.404, green: 0.314, blue: 0.643))
            .accessibilityLabel("Скопировать")
        }
    }
}

private final class StopwatchViewModelWrapper: ObservableObject {
    private let viewModel = StopwatchViewModel(clipboard: IosClipboard())
    private var observer: Cancellable?
    
    @Published private(set) var uiState = StopwatchUiState(
        currentTimeMillis: 0,
        initialTimeMillis: 0
    )
    
    func onStartClicked() {
        viewModel.onStartClicked()
    }
    
    func onStopClicked() {
        viewModel.onStopClicked()
    }

    func onCopyClicked() {
        viewModel.onCopyClicked()
    }
    
    init() {
        observer = viewModel.uiState.collect {
            [weak self] value in self?.uiState = value
        }
    }
    
    deinit {
        observer?.cancel()
        viewModel.onDestroy()
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
