package com.vclues.core.repository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;

import com.google.code.ssm.api.InvalidateSingleCache;
import com.google.code.ssm.api.ParameterValueKeyProvider;
import com.google.code.ssm.api.ReadThroughSingleCache;
import com.vclues.core.entity.Story;
import com.vclues.core.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	@ReadThroughSingleCache(namespace = "findByEmail")
    @Query("SELECT u FROM User u WHERE LOWER(u.email) = LOWER(:email)")
    User findByEmail(@ParameterValueKeyProvider @Param("email") String email);
	
	@ReadThroughSingleCache(namespace = "findByEmailAndActivated")
    @Query("SELECT u FROM User u WHERE LOWER(u.email) = LOWER(:email) and u.activated = :activated")
    User findByEmailAndActivated(@ParameterValueKeyProvider @Param("email") String email, @ParameterValueKeyProvider @Param("activated") boolean activated);	
	
    /*
    @Modifying(clearAutomatically = true)
    @Query("update User d set d.active = 0 where d.id =:id")
    public void deleteUser(@Param("id") Long id);
    */
    
    @Query
    List<User> findByActivated(boolean activated);
    
    @Query
    User findByEmailAndActivationKey(String email, String activationKey);

    @Query
    User findByEmailAndResetPasswordKey(String email, String resetPasswordKey);

	/**
	 * Find all accounts with the given lastname. This method will be translated
	 * into a query by constructing it directly from the method name as there is
	 * no other query declared.
	 * 
	 * @param lastname
	 * @return
	 */
	List<User> findByLastName(String lastname);

	/**
	 * Returns all accounts with the given firstname. This method will be
	 * translated into a query using the one declared in the {@link Query}
	 * annotation declared one.
	 * 
	 * @param firstname
	 * @return
	 */
	@Query("select u from User u where u.firstName = :firstName")
	List<User> findByFirstName(String firstname);

	/**
	 * Returns all Accounts with the given name as first- or lastname. This makes
	 * the query to method relation much more refactoring-safe as the order of
	 * the method parameters is completely irrelevant.
	 * 
	 * @param name
	 * @return
	 */
	@Query("select u from User u where lower(u.firstName) = lower(:name) or lower(u.lastName) = lower(:name) or lower(u.email) = lower(:name)")
	List<User> findByFirstNameOrLastName(@Param("name") String name);

	/**
	 * Returns the total number of entries deleted as their lastnames match the
	 * given one.
	 * 
	 * @param lastname
	 * @return
	 */
	//Long removeByLastName(String lastname);

	/**
	 * Returns a {@link Slice} counting a maximum number of
	 * {@link Pageable#getPageSize()} accounts matching given criteria starting at
	 * {@link Pageable#getOffset()} without prior count of the total number of
	 * elements available.
	 * 
	 * @param lastname
	 * @param page
	 * @return
	 */
	Slice<User> findByLastNameOrderByEmailAsc(String lastname, Pageable page);

	/**
	 * Return the first 2 accounts ordered by their lastname asc.
	 * 
	 * <pre>
	 * Example for findFirstK / findTopK functionality.
	 * </pre>
	 * 
	 * @return
	 */
	List<User> findFirst2ByOrderByLastNameAsc();

	/**
	 * Return the first 2 accounts ordered by the given {@code sort} definition.
	 * 
	 * <pre>
	 * This variant is very flexible because one can ask for the first K results when a ASC ordering
	 * is used as well as for the last K results when a DESC ordering is used.
	 * </pre>
	 * 
	 * @param sort
	 * @return
	 */
	List<User> findTop2By(Sort sort);

	/**
	 * Return all the accounts with the given firstname or lastname. Makes use of
	 * SpEL (Spring Expression Language).
	 *
	 * @param account
	 * @return
	 */
	@Query("select u from User u where u.firstName = :#{#user.firstName} or u.lastName = :#{#user.lastName}")
	Iterable<User> findByFirstNameOrLastName(@Param("user") User account);
	
	Iterable<User> findByParentUser(@Param("user") User account);	

	@Async
	CompletableFuture<List<User>> readAllBy();
	
	@Override
	@InvalidateSingleCache(namespace = "findByEmail")
	public User save(@ParameterValueKeyProvider User user);
}