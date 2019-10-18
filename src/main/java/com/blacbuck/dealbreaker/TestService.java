package com.blacbuck.dealbreaker;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TestService {

    @Autowired
    private Table2Repository table2Repository;

    @Autowired
    private Table1Repository table1Repository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private AddressRepository addressRepository;


    @Autowired
    private PersonDAL personDAL;

    @Transactional
    public void getDataFromTable2() {
        List<Table1> table1 = getData();
        table1.forEach(Hibernate::isInitialized);


    }

    public List<Table1> getData() {
        List<Table2> table2 = table2Repository.findAllByName("abc");
        return table2.stream().map(Table2::getTable1).collect(Collectors.toList());
    }

    @Transactional
    public void setUpdateT1Data(UpdateT1Data updateT1Data) {
        Table1 table1 = table1Repository.getOne(updateT1Data.getId());
        table1.setName(updateT1Data.getName());
        table1Repository.save(table1);
    }

    @Transactional
    public void createOrUpdatePerson(List<UpdatePersonData> updatePersonData) {

        updatePersonData.forEach(personData -> {
            Person person = new Person();
            Address address = new Address();
            address.setAddressData(personData.getAddress());
            address.setCity(personData.getCity());
            address.setState(personData.getState());
            person.setName(personData.getName());
            person.setAddress(address);
            personRepository.save(person);

        });

//       List<Person> personList =  updatePersonData.stream().map(personData -> {
//            Person person = new Person();
//            Address address = new Address();
//            address.setAddressData(personData.getAddress());
//            address.setCity(personData.getCity());
//            address.setState(personData.getState());
//            person.setName(personData.getName());
//            person.setAddress(address);
//            return person;
//
//        }).collect(Collectors.toList());
//        personRepository.saveAll(personList);

    }

    @Transactional
    public List<Person> getAddressData(Long id){
//      Optional<Address> address =  addressRepository.findById(id);
//      List<Person> personList = address.get().getPersonList();
        return personDAL.getPersonsListForAddressIdEager(id);
    }

    @Transactional
    public Person getPerson(Long id){
//      Optional<Address> address =  addressRepository.findById(id);
//      List<Person> personList = address.get().getPersonList();
        Person person = personDAL.getPersonsWithAddressEager(id);
        Address address  = person.getAddress();
        return person;
    }

}
