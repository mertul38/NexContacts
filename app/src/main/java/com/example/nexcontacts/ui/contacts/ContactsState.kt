import com.example.nexcontacts.data.remote.dto.UserDto

data class ContactsState(
    val search: String = "",
    val users: List<UserDto> = emptyList(),
    val groupedUsers: Map<Char, List<UserDto>> = emptyMap(),
    val error: String? = null,
)

