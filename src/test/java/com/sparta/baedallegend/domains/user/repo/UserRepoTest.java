package com.sparta.baedallegend.domains.user.repo;

import static com.sparta.baedallegend.global.fixture.UserFixtureGenerator.generateUserFixture;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import com.sparta.baedallegend.global.base.JpaTestBase;
import com.sparta.baedallegend.domains.user.domain.User;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Repo:User")
class UserRepoTest extends JpaTestBase {

	private final UserRepo userRepo;

	public UserRepoTest(UserRepo userRepo) {
		this.userRepo = userRepo;
	}

	@Test
	@DisplayName("회원 저장")
	void save() {
		// Given
		User given = generateUserFixture();

		// When
		User actual = userRepo.save(given);

		// Then
		assertThat(actual).isNotNull();
	}

	@Test
	@DisplayName("회원 조회")
	void findById() {
		// Given
		User given = userRepo.save(generateUserFixture());
		flushAndClear();

		// When
		User actual = userRepo.findById(given.getId()).orElseThrow();

		// Then
		assertThat(actual).isNotNull();
	}

	@Test
	@DisplayName("회원 삭제")
	void delete() {
		User given = userRepo.save(generateUserFixture());

		// When
		given.delete();
		flushAndClear();

		// Then
		assertThatExceptionOfType(NoSuchElementException.class)
			.isThrownBy(
				() -> userRepo.findById(given.getId())
					.orElseThrow()
			);
	}

}
