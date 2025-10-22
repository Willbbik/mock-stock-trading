
const Gnb = () => {
    return (
        <nav className="gnb-navbar">
            <div className="gnb-container">
                <div className="gnb-container__inner">
                    <div className="gnb-content">
                        <ul className="gnb-menu">
                            <li className="gnb-item">
                                <a target={'_blank'}>
                                    <div>
                                        차트
                                    </div>
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </nav>
    );
};

export default Gnb;