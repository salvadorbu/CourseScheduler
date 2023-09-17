import React, { useState } from 'react';
import axios from 'axios';
import 'bootstrap/dist/css/bootstrap.min.css'; // Import Bootstrap CSS
import './App.css';

const App = () => {
  const [subject, setSubject] = useState('');
  const [classNumber, setClassNumber] = useState('');
  const [tableData, setTableData] = useState([]);
  const [accessibilityMode, setIsSwitchOn] = useState(false);

  const handleSwitchChange = () => {
    setIsSwitchOn(!accessibilityMode);
  };

  const handleSubjectChange = (event) => {
    setSubject(event.target.value);
  };

  const handleClassNumberChange = (event) => {
    setClassNumber(event.target.value);
  };

  const handleAddRow = () => {
    if (subject && classNumber) {
      const newRow = {
        subject,
        classNumber,
      };

      setTableData([...tableData, newRow]);
      setSubject('');
      setClassNumber('');
    }
  };

  const handleDeleteRow = (index) => {
    const updatedTableData = [...tableData];
    updatedTableData.splice(index, 1);
    setTableData(updatedTableData);
  };

  const submit = () => {
    const requestData = tableData.map((row) => ({
      subject: row.subject,
      subjectID: row.classNumber, 
      accessibleRouting: accessibilityMode,
    }));
    console.log(requestData);
    axios.post('/submit', requestData)
      .then((response) => {
        console.log('Data sent successfully:', response.data);
      })
      .catch((error) => {
        console.error('Error sending data:', error);
      });
  };

  return (
    <div className="container mt-5 dashboard-background">
    <div className="row d-flex justify-content-center">
      <h1 className="text-center" style={{ fontFamily: 'Eiko', fontSize: '50px', marginBottom: "2%"}}>Course Curve</h1>
      <div className="mb-3 col-md-6">
        <label htmlFor="subject" className="form-label">Subject:</label>
        <select id="subject" className="form-select" value={subject} onChange={handleSubjectChange}>
          <option value="">Select a Subject</option>
          <option value="AOE">AOE - Aerospace and Ocean Engineering</option>
          <option value="ALCE">ALCE - Agricultural, Leadership, and Community Education</option>
          <option value="AAEC">AAEC - Agricultural and Applied Economics</option>
          <option value="ALS">ALS - Agriculture and Life Sciences</option>
          <option value="AINS">AINS - American Indian Studies</option>
          <option value="APSC">APSC - Animal and Poultry Sciences</option>
          <option value="APS">APS - Appalachian Studies</option>
          <option value="AHRM">AHRM - Apparel, Housing, and Resource Management</option>
          <option value="ARBC">ARBC - Arabic</option>
          <option value="ARCH">ARCH - Architecture</option>
          <option value="AAD">AAD - Africana Studies</option>
          <option value="ART">ART - Art and Art History</option>
          <option value="BDS">BDS - Behavioral Decision Science</option>
          <option value="BCHM">BCHM - Biochemistry</option>
          <option value="BIOL">BIOL - Biological Sciences</option>
          <option value="BSE">BSE - Biological Systems Engineering</option>
          <option value="BMVS">BMVS - Biomedical & Veterinary Sciences</option>
          <option value="BMSP">BMSP - Biomedical Sciences and Pathobiology</option>
          <option value="BMES">BMES - Biomedical Engineering and Sciences</option>
          <option value="BC">BC - Building Construction</option>
          <option value="BUS">BUS - Business</option>
          <option value="BIT">BIT - Business Information Technology</option>
          <option value="EDCT">EDCT - Career and Technical Education</option>
          <option value="CHE">CHE - Chemical Engineering</option>
          <option value="CHEM">CHEM - Chemistry</option>
          <option value="CHN">CHN - Chinese</option>
          <option value="CINE">CINE - Cinema</option>
          <option value="CEE">CEE - Civil and Environmental Engineering</option>
          <option value="CLA">CLA - Classics</option>
          <option value="COS">COS - College of Science</option>
          <option value="COMM">COMM - Communication</option>
          <option value="CMST">CMST - Communication Studies</option>
          <option value="CMDA">CMDA - Computational Modeling and Data Analytics</option>
          <option value="CS">CS - Computer Science</option>
          <option value="CEM">CEM - Construction Engineering and Management</option>
          <option value="CONS">CONS - Consumer Studies</option>
          <option value="CEP">CEP - Counselor Education</option>
          <option value="CRIM">CRIM - Criminology</option>
          <option value="CSES">CSES - Crop and Soil Environmental Sciences</option>
          <option value="DASC">DASC - Dairy Science</option>
          <option value="DANC">DANC - Dance</option>
          <option value="ECON">ECON - Economics</option>
          <option value="EDCO">EDCO - Counselor Education</option>
          <option value="EDCI">EDCI - Curriculum and Instruction</option>
          <option value="EDEP">EDEP - Educational Psychology</option>
          <option value="ECE">ECE - Electrical and Computer Engineering</option>
          <option value="ENGR">ENGR - Engineering</option>
          <option value="ENGE">ENGE - Engineering Education</option>
          <option value="ESM">ESM - Engineering Science and Mechanics</option>
          <option value="ENGL">ENGL - English</option>
          <option value="ENT">ENT - Entomology</option>
          <option value="ENSC">ENSC - Environmental Science</option>
          <option value="FCS">FCS - Family and Consumer Sciences</option>
          <option value="FMD">FMD - Fashion Merchandising</option>
          <option value="FIN">FIN - Finance, Insurance, Business Law</option>
          <option value="FNAD">FNAD - Fine Arts</option>
          <option value="FA">FA - Fine Arts</option>
          <option value="FIW">FIW - Fisheries and Wildlife Sciences</option>
          <option value="FST">FST - Food Science and Technology</option>
          <option value="FL">FL - Foreign Language</option>
          <option value="FREC">FREC - Forest Resources and Environmental Conservation</option>
          <option value="VT">VT - Veterinary Medicine</option>
          <option value="FR">FR - French</option>
          <option value="GEOG">GEOG - Geography</option>
          <option value="GEOS">GEOS - Geosciences</option>
          <option value="GER">GER - German</option>
          <option value="GR">GR - Greek</option>
          <option value="HEB">HEB - Hebrew</option>
          <option value="HIST">HIST - History</option>
          <option value="HORT">HORT - Horticulture</option>
          <option value="HTM">HTM - Hospitality and Tourism Management</option>
          <option value="HD">HD - Human Development</option>
          <option value="HNFE">HNFE - Human Nutrition, Foods and Exercise</option>
          <option value="HUM">HUM - Humanities</option>
          <option value="ISE">ISE - Industrial and Systems Engineering</option>
          <option value="IDS">IDS - Industrial Design</option>
          <option value="EDIT">EDIT - Instructional Design & Tech</option>
          <option value="ISC">ISC - Integrated Science</option>
          <option value="ITDS">ITDS - Interior Design</option>
          <option value="IS">IS - International Studies</option>
          <option value="ITAL">ITAL - Italian</option>
          <option value="JPN">JPN - Japanese</option>
          <option value="JMC">JMC - Journalism and Mass Communication</option>
          <option value="JUD">JUD - Judaic Studies</option>
          <option value="KOR">KOR - Korean</option>
          <option value="LAR">LAR - Landscape Architecture</option>
          <option value="LAT">LAT - Latin</option>
          <option value="LDRS">LDRS - Leadership Studies</option>
          <option value="LAHS">LAHS - Liberal Arts and Human Sciences</option>
          <option value="MGT">MGT - Management</option>
          <option value="MKTG">MKTG - Marketing</option>
          <option value="MSE">MSE - Materials Science and Engineering</option>
          <option value="MATH">MATH - Mathematics</option>
          <option value="ME">ME - Mechanical Engineering</option>
          <option value="MTRG">MTRG - Meteorology</option>
          <option value="MN">MN - Military Navy (NROTC)</option>
          <option value="AROTC">AROTC - Army ROTC</option>
          <option value="MS">MS - Military Science (AROTC)</option>
          <option value="AS">AS - Aerospace Studies (AFROTC)</option>
          <option value="MINE">MINE - Mining and Minerals Engineering</option>
          <option value="MUS">MUS - Music</option>
          <option value="NANO">NANO - Nanoscience</option>
          <option value="NR">NR - Natural</option>
        </select>
      </div>
      <div className="mb-3 col-md-6" >
        <label htmlFor="classNumber" className="form-label">Class Number:</label>
        <input
          type="text"
          id="classNumber"
          className="form-control"
          value={classNumber}
          onChange={handleClassNumberChange}
        />
      </div>
      </div>

      <div className="col-md-6 mb-3">
        <button className="btn btn-dark" onClick={handleAddRow}>Add Row</button>
        <button className="btn btn-dark" onClick={submit} style={{ marginLeft: '10px' }}>Generate Schedule</button>
          <label className="switch" style = {{marginLeft: '10px'}}>
            <input type="checkbox" onChange={handleSwitchChange} checked={accessibilityMode} />
            <span className="slider round"></span>
          </label>
          {/* <p>Accessibility Mode {accessibilityMode ? 'ON' : 'OFF'}</p> */}
      </div>

      <table className="table mt-3">
        <thead>
          <tr>
            <th>Subject</th>
            <th>Class Number</th>
            <th></th>
          </tr>
        </thead>
        <tbody>
          {tableData.map((row, index) => (
            <tr key={index}>
              <td style={{verticalAlign: "middle"}}>{row.subject}</td>
              <td style={{verticalAlign: "middle"}}>{row.classNumber}</td>
              <td style={{ textAlign: 'right'}}>
                <button onClick={() => handleDeleteRow(index)}>Delete</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>

  );
};

export default App;
