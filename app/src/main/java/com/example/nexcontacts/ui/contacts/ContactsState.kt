import com.example.nexcontacts.domain.model.User

data class ContactsState(
    val search: String = "",
    val searchResults: List<User> = emptyList(),
    val users: List<User> = emptyList(),
    val groupedUsers: Map<Char, List<User>> = emptyMap(),
    val error: String? = null,
    val isLoading: Boolean = false
)

