import com.example.nexcontacts.data.remote.dto.UserDto

data class GetAllUsersResponse(
    val success: Boolean,
    val messages: List<String>,
    val data: UsersData?,
    val status: Int
)

data class UsersData(
    val users: List<UserDto>
)
